package hwr.oop.persistence;

public class CantSaveAppDataException extends RuntimeException{
    public CantSaveAppDataException(String message) {
        super(message);
    }
}
