package hwr.oop.group4.todo;

import java.time.LocalDateTime;
import java.util.Set;

public class Task {

    private String name;
    private String description;
    // higher value means higher priority
    private int priority;
    private LocalDateTime deadline;
    private final Set<Tag> tags;

    public Task(String name, String description, int priority, LocalDateTime deadline, Set<Tag> tags) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.tags = tags;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }
}
