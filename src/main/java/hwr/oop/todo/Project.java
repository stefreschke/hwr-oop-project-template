package hwr.oop.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private final String title;
    private ArrayList<Task> task_list;
    private LocalDate deadline;

    public Project(String title , ArrayList<Task> task_list, LocalDate deadline) {
        this.title = title;
        this.task_list = task_list;
        this.deadline = deadline;
    }

    public void addTask(Task task) {
        this.task_list.add(task);
    }

    public List<Task> getTasklist() {
        return this.task_list;
    }

    public Task getLastTask() {
        int pos = this.task_list.size();
        return this.task_list.get(pos-1);
    }

    public String Title() {
        return title;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }


}