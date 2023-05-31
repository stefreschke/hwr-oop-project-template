package hwr.oop.persistence;

public class ProjectNotInAppDataException extends RuntimeException{
    public ProjectNotInAppDataException(String errorMessage) { super(errorMessage); }
}
