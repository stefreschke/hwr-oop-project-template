package hwr.oop.group4.todo;

import java.time.LocalDateTime;
import java.util.Set;

public class Project {

    private String name;
    private String description;
    private final Set<Tag> tags;
    private final Set<Task> tasks;
    private LocalDateTime begin;
    private LocalDateTime end;

    public Project(String name, String description, Set<Tag> tags, Set<Task> tasks, LocalDateTime begin, LocalDateTime end) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.tasks = tasks;
        this.begin = begin;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
