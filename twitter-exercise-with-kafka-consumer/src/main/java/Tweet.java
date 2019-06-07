import java.util.Date;

public class Tweet {
    private String user;
    private String message;
    private Date date;

    public Tweet() {}

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

    public String toString() { return "[" + date + "]" + user + ": " + message; }
}