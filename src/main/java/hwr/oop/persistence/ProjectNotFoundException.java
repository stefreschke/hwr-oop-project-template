package hwr.oop.persistence;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String errorMessage) { super(errorMessage); }
}
