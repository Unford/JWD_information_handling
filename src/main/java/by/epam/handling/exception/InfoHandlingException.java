package by.epam.handling.exception;

public class InfoHandlingException extends Exception {

    public InfoHandlingException() {
        super();
    }

    public InfoHandlingException(String message) {
        super(message);
    }

    public InfoHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfoHandlingException(Throwable cause) {
        super(cause);
    }
}
