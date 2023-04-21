package hwr.oop.project;

import hwr.oop.task.Task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Project {
    private String title;
    private String description;
    private final LocalDateTime dateTimeCreated;
    private LocalDateTime dateTimeDeadline;
    private Set<Task> tasks = new HashSet<>();

    protected Project(String title, String description, LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.dateTimeDeadline = deadline;
        this.dateTimeCreated = LocalDateTime.now();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeDeadline(LocalDateTime deadline) {
        this.dateTimeDeadline = deadline;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDateTimeCreated() {
        return this.dateTimeCreated;
    }

    public LocalDateTime getDeadline() {
        return this.dateTimeDeadline;
    }

    public Set<Task> getTasks() {
        return this.tasks;
    }
}
