package hwr.oop.todo;

import java.util.HashMap;
import java.util.List;

public class TaskController {
    private HashMap<String, Task> tasks;

    public void addTask(Task task){
        String id = "id:abc";
        tasks.put(id, task);
    }

    public List<Tag> getTags(){
        return TagController.get().getTags();
    }

    public void getTask(Integer id){

    }
}
