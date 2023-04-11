package hwr.oop.todo;

import java.util.HashMap;
import java.util.List;

public class TaskController {
    private HashMap<String, Task> tasks;

    public void addTask(TaskData data){
        Task task = Task.fromData(data);
        tasks.put(task.getId(), task);
    }

    public void getTask(Integer id){

    }
}
