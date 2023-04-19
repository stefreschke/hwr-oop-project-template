package hwr.oop.group4.todo.commons.exceptions;

public class TodoRuntimeException extends RuntimeException {

    public TodoRuntimeException() {
    }

    public TodoRuntimeException(String message) {
        super(message);
    }

    public TodoRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TodoRuntimeException(Throwable cause) {
        super(cause);
    }

    public TodoRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
