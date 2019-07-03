package hellotwitter;

import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HelloTweet {

    public static void main(String args[]) {
        System.out.println("Hello, Tweet!");
        Cluster cluster = null;
        String keyspaceName = "tweets";

        try {
            cluster = Cluster.builder()
                    .addContactPoint("localhost")
                    .build();

            Session session = cluster.connect();

            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            System.out.println(row.getString("release_version"));

            System.out.println("Create repository " + keyspaceName);
            KeyspaceRepository sr = new KeyspaceRepository(session);
            sr.createKeyspace(keyspaceName, "SimpleStrategy", 1);

            System.out.println("Use repository " + keyspaceName);
            sr.useKeyspace(keyspaceName);

            TweetRepository tr = new TweetRepository(session);

            System.out.println("Cleaning tables if some mess happened...");
            tr.deleteTable("tweets");
            tr.deleteTable("tweetsByUser");

            System.out.println("Create table tweets");
            tr.createTable();

            System.out.println("Create table tweetsByUser");
            tr.createTableTweetsByUser();

            List<Long> contributorsList = new ArrayList<>();
            contributorsList.add((long) 1);
            contributorsList.add((long) 33474);
            System.out.println("Insert tweets");
            Tweet tweet = new Tweet(UUIDs.timeBased(), "User 1", "Wow, Cassandra is amazing!", LocalDate.fromYearMonthDay(2019, 6, 25), "Some source", false, -33, -21.673356, true, contributorsList);
            Tweet tweet2 = new Tweet(UUIDs.timeBased(), "User 1", "Look at what I can do with Cassandra and Java!", LocalDate.fromYearMonthDay(2019, 6, 24), "A crazy source", false, -3.235, 15, true, null);
            Tweet tweet3 = new Tweet(UUIDs.timeBased(), "User 3", "This is really impressive.", LocalDate.fromYearMonthDay(2019, 5, 25), "This source is amazing", false, -33.21256, -26.34356, false, null);
            Tweet tweet4 = new Tweet(UUIDs.timeBased(), "User 4", "I cant wait to learn more! I am so exc", LocalDate.fromYearMonthDay(2019, 8, 25), "Sent from Android", true, -20.233467, -12.378337, false, null);
            Tweet tweet5 = new Tweet(UUIDs.timeBased(), "User 5", "See you next time.", LocalDate.fromYearMonthDay(2020, 6, 25), "Somewhere over the rainbow", false, 0, 0, true, null);
            tr.insertTweet(tweet);
            tr.insertTweet(tweet2);
            tr.insertTweet(tweet3);
            tr.insertTweet(tweet4);
            tr.insertTweet(tweet5);

            System.out.println("Insert tweets by user");
            tr.insertTweetByUser(tweet);
            tr.insertTweetByUser(tweet2);
            tr.insertTweetByUser(tweet3);
            tr.insertTweetByUser(tweet4);
            tr.insertTweetByUser(tweet5);

            System.out.println("Select all tweets");
            tr.selectAll("tweets");

            System.out.println("Select all tweets by user");
            tr.selectAll("tweetsByUser");

            System.out.println("Select a tweet by User 1");
            tr.selectByUser("User 1");

            System.out.println("Delete tweet");
            tr.deleteTweet(tweet.getId());

            System.out.println("Delete tweet by user");
            tr.deleteTweetByUser("User 3");

            System.out.println("Select all tweets");
            tr.selectAll("tweets");

            System.out.println("Select all tweets by user");
            tr.selectAll("tweetsByUser");

            System.out.println("Delete table tweets");
            tr.deleteTable("tweets");

            System.out.println("Delete table tweets by user");
            tr.deleteTable("tweetsByUser");

            System.out.println("Delete keyspace " + keyspaceName);
            sr.deleteKeyspace(keyspaceName);
        }
        finally {
            if(cluster != null) cluster.close();
        }
    }
}
