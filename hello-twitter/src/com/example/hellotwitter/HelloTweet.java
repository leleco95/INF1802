package com.example.hellotwitter;

import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;

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

            System.out.println("Create table book");
            TweetRepository tr = new TweetRepository(session);
            tr.createTable();

            System.out.println("Insert tweets");
            Tweet tweet = new Tweet(UUIDs.timeBased(), "User 1", "Wow, Cassandra is amazing!", LocalDate.fromYearMonthDay(2019, 6, 25));
            Tweet tweet2 = new Tweet(UUIDs.timeBased(), "User 2", "Look at what I can do with Cassandra and Java!", LocalDate.fromYearMonthDay(2019, 6, 24));
            Tweet tweet3 = new Tweet(UUIDs.timeBased(), "User 3", "This is really impressive.", LocalDate.fromYearMonthDay(2019, 5, 25));
            Tweet tweet4 = new Tweet(UUIDs.timeBased(), "User 4", "I cant wait to learn more!", LocalDate.fromYearMonthDay(2019, 8, 25));
            Tweet tweet5 = new Tweet(UUIDs.timeBased(), "User 5", "See you next time.", LocalDate.fromYearMonthDay(2020, 6, 25));
            tr.insertTweet(tweet);
            tr.insertTweet(tweet2);
            tr.insertTweet(tweet3);
            tr.insertTweet(tweet4);
            tr.insertTweet(tweet5);

            System.out.println("Select all tweets");
            tr.selectAll();

            System.out.println("Delete tweets");
            tr.deleteTweet(tweet.getId());

            System.out.println("Delete table tweets");
            tr.deleteTable("tweets");

            System.out.println("Delete keyspace " + keyspaceName);
            sr.deleteKeyspace(keyspaceName);
        }
        finally {
            if(cluster != null) cluster.close();
        }
    }
}
