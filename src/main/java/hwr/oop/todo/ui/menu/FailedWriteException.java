package hwr.oop.todo.ui.menu;

public class FailedWriteException extends RuntimeException {
    public FailedWriteException(){
        super("Failed to write to console.");
    }

}
