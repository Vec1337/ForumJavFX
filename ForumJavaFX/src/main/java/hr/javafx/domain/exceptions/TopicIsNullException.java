package hr.javafx.domain.exceptions;

public class TopicIsNullException extends RuntimeException{
    public TopicIsNullException() {
    }

    public TopicIsNullException(String message) {
        super(message);
    }

    public TopicIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicIsNullException(Throwable cause) {
        super(cause);
    }

    public TopicIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
