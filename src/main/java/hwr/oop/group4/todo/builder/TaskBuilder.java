package hwr.oop.group4.todo.builder;

import hwr.oop.group4.todo.Tag;
import hwr.oop.group4.todo.Task;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskBuilder {

    private String name = "unnamed task";
    private String description = "";
    private LocalDateTime deadline = null;
    private int priority = 0;
    private final Set<Tag> tags = new HashSet<>();

    public TaskBuilder() {
    }

    public TaskBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }

    public TaskBuilder setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public TaskBuilder addTag(Tag tag) {
        tags.add(tag);
        return this;
    }

    public TaskBuilder addTags(Tag... tags) {
        this.tags.addAll(Arrays.asList(tags));
        return this;
    }

    public Task build() {
        return new Task(this);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public int getPriority() {
        return priority;
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
