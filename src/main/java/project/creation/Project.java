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
    public LocalDate getDate() {
        return this.date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
