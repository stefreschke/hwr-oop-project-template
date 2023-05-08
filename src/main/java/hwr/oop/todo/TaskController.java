package hwr.oop.todo;

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

    public Task addTask(TaskData taskdata){
        return todolist.addTask(taskdata);
    }

    public Task getTask(UUID id){
       return todolist.getTask(id);
    }


}

