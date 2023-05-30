package hwr.oop.persistence;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage) { super(errorMessage); }
}
