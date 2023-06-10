package hwr.oop.persistence;

public class PersistenceFileNotFoundException extends RuntimeException{
    public PersistenceFileNotFoundException(String s) {
        super(s);
    }
}
