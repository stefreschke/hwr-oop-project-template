package hwr.oop.group4.todo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Task {

    private final String name;
    private final String description;
    /**
     * higher value means higher priority
     */
    private final int priority;
    private final LocalDateTime deadline;
    private final Set<Tag> tags;
    private final Status status = Status.OPEN;

    private Task(String name, String description, LocalDateTime deadline, int priority, Set<Tag> tags, Project project) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.tags = tags;
        if (project != null) {
            project.addTask(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return priority == task.priority && Objects.equals(name, task.name) && Objects.equals(description, task.description)
                && Objects.equals(deadline, task.deadline) && Objects.equals(tags, task.tags) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, priority, deadline, tags, status);
    }

    public static class TaskBuilder {

        private String name = "unnamed task";
        private String description = "";
        private LocalDateTime deadline = null;
        private int priority = 0;
        private final Set<Tag> tags = new HashSet<>();
        private Project project;

        public TaskBuilder() {
        }

        public TaskBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder deadline(LocalDateTime deadline) {
            this.deadline = deadline;
            return this;
        }

        public TaskBuilder priority(int priority) {
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

        public TaskBuilder project(Project project) {
            this.project = project;
            return this;
        }

        public TaskBuilder fromIdea(Idea idea) {
            this.name = idea.getName();
            this.description = idea.getDescription();
            return this;
        }

        public Task build() {
            return new Task(name, description, deadline, priority, tags, project);
        }
    }

}
