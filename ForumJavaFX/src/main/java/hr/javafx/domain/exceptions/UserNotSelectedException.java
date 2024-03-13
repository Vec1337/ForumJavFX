package hr.javafx.domain.exceptions;

import java.io.IOException;

public class UserNotSelectedException extends IOException {
    public UserNotSelectedException() {
    }

    public UserNotSelectedException(String message) {
        super(message);
    }

    public UserNotSelectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotSelectedException(Throwable cause) {
        super(cause);
    }
}
