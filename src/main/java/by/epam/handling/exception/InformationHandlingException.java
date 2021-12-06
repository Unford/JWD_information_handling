package by.epam.handling.exception;

public class InformationHandlingException extends Exception {

    public InformationHandlingException() {
        super();
    }

    public InformationHandlingException(String message) {
        super(message);
    }

    public InformationHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InformationHandlingException(Throwable cause) {
        super(cause);
    }
}
