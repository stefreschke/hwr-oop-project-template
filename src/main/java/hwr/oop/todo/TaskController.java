package hwr.oop.todo;

import java.util.HashMap;
import java.util.List;

// TODO:
//  - Controller testen
//  - Tag
//  - Project (kann ggf. von Tag erben)

public class TaskController {
    private HashMap<String, Task> tasks;
    private TagController tagController = TagController.get();


    public void addTask(Task task){
        // TODO: Generate uuid
        String id = "id:abc";
        tasks.put(id, task);
    }

    public List<String> getTags(){
        return this.tagController.getTags();
    }

    public void getTask(Integer id){

    }
}
