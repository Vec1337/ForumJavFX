package hr.javafx.domain.exceptions;

public class RequiredFieldsNotEnteredException extends Exception{
    public RequiredFieldsNotEnteredException() {
    }

    public RequiredFieldsNotEnteredException(String message) {
        super(message);
    }

    public RequiredFieldsNotEnteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredFieldsNotEnteredException(Throwable cause) {
        super(cause);
    }

    public RequiredFieldsNotEnteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
