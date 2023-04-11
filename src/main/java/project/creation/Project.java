package project.creation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Project {

    String name;
    LocalDateTime time;
    public List<Task> tasks = new ArrayList<Task>();
    LocalDate date;

    public Project(String name, LocalDateTime time, LocalDate date){
        this.name = name;
        this.time = time;
        this.date = date;
    }


    public void addTask(Task task){
        this.tasks.add(task);
    }
    public void removeTask(Task task) {
        this.tasks.remove(task);
    }
    public Project getProject(){
        return new Project(name, time, date);
    }
    public void changeName(String name){
        this.name = name;
    }
    public void changeDeadline(LocalDate date){
        this.date = date;
    }
    public LocalDate getDate() {
        return this.date;
    }
}
