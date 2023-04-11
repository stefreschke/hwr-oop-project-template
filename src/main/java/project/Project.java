package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    LocalDateTime time;
    List<Task> tasks = new ArrayList<Task>();
    LocalDate date;

    Project(String name, LocalDateTime time, LocalDate date){
        this.name = name;
        this.time = time;
        this.date = date;
    }


    public void addTask(Task task){
        this.tasks.add(task);
    }

}
