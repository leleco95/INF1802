import java.util.Date;

public class Tweet {
    private final String user;
    private final String message;
    private final Date date;

    public Tweet(final String user, final String message, final Date date) {
        this.user = user;
        this.message = message;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}