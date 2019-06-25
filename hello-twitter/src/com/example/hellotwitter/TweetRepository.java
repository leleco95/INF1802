package com.example.hellotwitter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class TweetRepository {

    private static final String TABLE_NAME = "tweets";
    private Session session;
    public TweetRepository(Session session) { this.session = session; }

    public void createTable() {
        System.out.println("createTable ---- init");
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("id uuid PRIMARY KEY,")
                .append("user text,")
                .append("message text,")
                .append("date date);");

        final String query = sb.toString();
        session.execute(query);
        System.out.println("createTable ---- end");
    }

    public void insertTweet(Tweet tweet) {
        System.out.println("insertTweet ---- init");
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME).append("(id, user, message, date) ")
                .append("VALUES (").append(tweet.getId()).append(", '")
                .append(tweet.getUser()).append("', '")
                .append(tweet.getMessage()).append("', '")
                .append(tweet.getDate()).append("');");

        final String query = sb.toString();
        session.execute(query);
        System.out.println("insertTweet ---- end");
    }

    public List<Tweet> selectAll() {
        System.out.println("selectAll ---- init");
        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);

        final String query = sb.toString();
        ResultSet rs = session.execute(query);
        List<Tweet> tweets = new ArrayList<>();

        for(Row r : rs) {
            Tweet tweet = new Tweet(r.getUUID("id"), r.getString("user"), r.getString("message"), r.getDate("date"));
            System.out.println("Tweet = " + tweet.getId() + ", "
                    + tweet.getUser() + ", "
                    + tweet.getMessage() + ", "
                    + tweet.getDate());
            tweets.add(tweet);
        }

        System.out.println("selectAll ---- end");
        return tweets;
    }

    public void deleteTweet(UUID id) {
        System.out.println("deleteTweet ---- init");
        StringBuilder sb = new StringBuilder("DELETE FROM ")
                .append(TABLE_NAME)
                .append(" WHERE id = ")
                .append(id.toString()).append(";");

        final String query = sb.toString();
        session.execute(query);
        System.out.println("deleteTweet ---- end");
    }

    public void deleteTable(String tableName) {
        System.out.println("deleteTable ---- init");
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);

        final String query = sb.toString();
        session.execute(query);
        System.out.println("deleteTable ---- end");
    }
}
