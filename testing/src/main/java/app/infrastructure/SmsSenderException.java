package app.infrastructure;

public class SmsSenderException extends Exception {
    public SmsSenderException(String responseCode) {
        super("SMS Response code: " + responseCode);
    }
}
