package hwr.oop.todo;

import java.util.HashMap;
import java.util.UUID;

public class ToDoList {
    private final HashMap<UUID, Task> tasks = new HashMap<>();

    public Task addTask(TaskData taskData){
        UUID id = UUID.randomUUID();
        Task task =  Task.fromData(id, taskData);

        tasks.put(id, task);

        return task;
    }

    public TaskData getTask(UUID id){
        TaskData taskData = tasks.get(id);
        if(taskData == null){
            throw new ToDoListException("Task does not exist");
        }

        return tasks.get(id);
    }
}
