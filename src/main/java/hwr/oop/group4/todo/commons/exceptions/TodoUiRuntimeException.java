package hwr.oop.group4.todo.commons.exceptions;

public class TodoUiRuntimeException extends RuntimeException {

    public TodoUiRuntimeException() {
    }

    public TodoUiRuntimeException(String message) {
        super(message);
    }

    public TodoUiRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TodoUiRuntimeException(Throwable cause) {
        super(cause);
    }

    public TodoUiRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
