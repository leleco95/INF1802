import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.Serializable;

public class TweetLifecycleManager implements LifecycleManager, Serializable {

    private String _consumerKey;
    private String _consumerSecret;
    private String _accessToken;
    private String _accessTokenSecret;
    private TwitterStreamFactory twitterStreamFactory;

    public TweetLifecycleManager() {
        this._consumerKey = System.getenv().get("TWITTER_CONSUMER_KEY");
        this._consumerSecret = System.getenv().get("TWITTER_CONSUMER_SECRET");
        this._accessToken = System.getenv().get("TWITTER_ACCESS_TOKEN");
        this._accessTokenSecret = System.getenv().get("TWITTER_ACCESS_TOKEN_SECRET");
    }

    public void start() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(this._consumerKey)
                .setOAuthConsumerSecret(this._consumerSecret)
                .setOAuthAccessToken(this._accessToken)
                .setOAuthAccessTokenSecret(this._accessTokenSecret);
        this.twitterStreamFactory = new TwitterStreamFactory(configurationBuilder.build());
        TwitterStream twitterStream = twitterStreamFactory.getInstance();
        StatusListener listener = new StatusListener() {
            public void onStatus(Status status) {
                Tweet t = new Tweet(status.getUser().getName(), status.getText(), status.getCreatedAt());
                System.out.println("[" + t.getDate() + "]" + t.getUser() + ": " + t.getMessage());
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatus) {}
            public void onScrubGeo(long l, long l1) {}
            public void onStallWarning(StallWarning stallWarning) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);

        String trackedTerms = "praia";
        FilterQuery query = new FilterQuery();
        query.track(trackedTerms.split(","));
        twitterStream.filter(query);
    }

    public void stop() {
        TwitterStream twitterStream = twitterStreamFactory.getInstance();
        twitterStream.shutdown();
    }

}