package hwr.oop.group4.todo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Project {

    private final String name;
    private final String description;
    private final Set<Tag> tags;
    private final Set<Task> tasks;
    private final LocalDateTime begin;
    private final LocalDateTime end;

    public Project(String name, String description) {
        this(name, description, new HashSet<>(), new HashSet<>(), null, null);
    }

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


    public String getDescription() {
        return description;
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

    public LocalDateTime getEnd() {
        return end;
    }

}
