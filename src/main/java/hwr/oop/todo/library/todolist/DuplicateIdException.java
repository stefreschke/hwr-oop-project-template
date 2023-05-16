package hwr.oop.todo.library.todolist;

import java.util.UUID;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(UUID id){
        super("The id "+id.toString()+" is not available.");
    }
}
