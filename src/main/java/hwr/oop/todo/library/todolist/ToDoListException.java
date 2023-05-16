package hwr.oop.todo.library.todolist;

import java.util.UUID;

public class ToDoListException extends RuntimeException{
    public ToDoListException(String message){
        super(message);
    }
}
