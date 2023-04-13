package hwr.oop.todo;

import java.util.HashMap;

// TODO:
//  - Controller testen
//  - Tag
//  - Project (kann ggf. von Tag erben)

public class TaskController {
    private HashMap<String, Task> tasks;

    public void addTask(Task task){
        // TODO: Generate uuid
        String id = "id:abc";
        tasks.put(id, task);
    }

    public void getTask(Integer id){

    }
}
