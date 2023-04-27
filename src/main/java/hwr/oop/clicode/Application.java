package hwr.oop.clicode;

import hwr.oop.dataclasses.Task;
import hwr.oop.dataclasses.TaskList;

import java.util.ArrayList;

public class Application {

    private TaskList taskList;

    public Application() {
        taskList = new TaskList(new ArrayList<>());
    }

    public static void main(String[] args) {
        System.out.println("we have a main class");
    }

    public void deleteTask(Task toBeRemoved) {
        taskList.deleteTask(toBeRemoved);
    }
}
