package hwr.oop.persistence;

public class CantLoadAppDataException extends RuntimeException {
    public CantLoadAppDataException(String message) {
        super(message);
    }
}
