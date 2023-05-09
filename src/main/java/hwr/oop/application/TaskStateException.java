package hwr.oop.application;

public class TaskStateException extends RuntimeException {
    public TaskStateException(String errorMessage) {
        super(errorMessage);
    }
}
