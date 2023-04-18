package hwr.oop.todo;

import java.util.HashMap;
import java.util.UUID;

public class TaskController {
    private final HashMap<UUID, Task> tasks = new HashMap<>();

    public UUID addTask(Task task){

    public void addTask(Task task){
        UUID id = UUID.randomUUID();
        tasks.put(id, task);
        return id;
    }

    public List<Tag> getTags(){
        return TagController.get().getTags();
    }

    public Task getTask(UUID id){
      return tasks.get(id);
    }
}
