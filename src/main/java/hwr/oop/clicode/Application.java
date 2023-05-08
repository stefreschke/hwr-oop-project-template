package hwr.oop.clicode;

import hwr.oop.dataclasses.Task;
import hwr.oop.dataclasses.TaskList;
import hwr.oop.dataclasses.TaskState;
import hwr.oop.dataclasses.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class Application {

    private TaskList taskList;

    public Application() {
        taskList = new TaskList(new ArrayList<>());
    }

    //create a new User
    public User createUser(String name, Integer id) {
        return new User(name, id);
    }

    public Task getTaskByID(Integer id){
        return taskList.getTasks().stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    public void addTask(String title, String content, TaskState taskState, User creator, LocalDate deadline){
        Task tmp = new Task(0,title,content,taskState,null,creator,deadline);
        taskList.addTask(tmp);
    }

    public void deleteTask(Task toBeRemoved) {
        taskList.deleteTask(toBeRemoved);
    }
}
