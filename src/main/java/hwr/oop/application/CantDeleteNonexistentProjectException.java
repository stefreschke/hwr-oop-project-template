package hwr.oop.application;

public class CantDeleteNonexistentProjectException extends RuntimeException{

    public CantDeleteNonexistentProjectException(String message) {
        super(message);
    }
}
