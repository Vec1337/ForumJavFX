package hr.javafx.domain.exceptions;

import java.io.IOException;

public class TopicIsNotSelectedException extends IOException {
    public TopicIsNotSelectedException() {
    }

    public TopicIsNotSelectedException(String message) {
        super(message);
    }

    public TopicIsNotSelectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicIsNotSelectedException(Throwable cause) {
        super(cause);
    }
}
