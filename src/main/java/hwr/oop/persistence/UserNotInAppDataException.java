package hwr.oop.persistence;

public class UserNotInAppDataException extends RuntimeException {
    public UserNotInAppDataException(String errorMessage) { super(errorMessage); }
}
