package hwr.oop.todo.ui.menu;

public class CantWriteException extends RuntimeException {
    public CantWriteException(){
        super("Failed to write to console.");
    }

}
