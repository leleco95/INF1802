import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;

import java.util.Arrays;
import java.util.Properties;


public class FavoriteColorExercise {
    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "favoritecolor-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        KStreamBuilder builder = new KStreamBuilder();

        // need to fix it so it works as table, not stream
        String[] possibleColors = {"vermelho", "verde", "azul"};
        KStream<String, String> colorsStream = builder.stream("colors-input");
        KTable<String, Long> colorCounts = colorsStream
                .mapValues(line -> line.toLowerCase())
                .filter((key, value) -> value.matches("\\w+, \\w+"))
                .selectKey((key, value) -> value.split("\\W+")[0])
                .mapValues(line -> line.split("\\W+")[1])
                .filter((key, value) -> Arrays.asList(possibleColors).contains(value))
                .groupBy((key, value) -> value)
                .count("Counts");
        colorCounts.to(Serdes.String(), Serdes.Long(), "colors-output");

        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
