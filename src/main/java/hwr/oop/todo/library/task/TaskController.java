package hwr.oop.todo.library.task;

import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class TaskController {
    private final ToDoList todolist = new ToDoList();

    private static final TaskController self = new TaskController();

    public static TaskController get() {
        return self;
    }

    public ToDoList getToDoList() {
        return todolist;
    }

    public void addTask(Task task){
        todolist.addTask(task);
    }

    public Task getTask(UUID id){
       return todolist.getTask(id);
    }


}

