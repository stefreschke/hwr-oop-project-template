package hwr.oop.group4.todo.core;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Project {

    private final String name;
    private final String description;
    private final Set<Tag> tags;
    private final Set<Task> tasks;
    private final LocalDateTime begin;
    private final LocalDateTime end;

    private Project(String name, String description, Set<Tag> tags, Set<Task> tasks, LocalDateTime begin, LocalDateTime end) {
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

    public static class ProjectBuilder {

        private String name = "";
        private String description = "";
        private final Set<Tag> tags = new HashSet<>();
        private final Set<Task> tasks = new HashSet<>();
        private LocalDateTime begin;
        private LocalDateTime end;

        public ProjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProjectBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProjectBuilder addTag(Tag tag) {
            tags.add(tag);
            return this;
        }

        public ProjectBuilder addTag(Tag... tags) {
            this.tags.addAll(List.of(tags));
            return this;
        }

        public ProjectBuilder addTask(Task task) {
            tasks.add(task);
            return this;
        }

        public ProjectBuilder addTasks(Task... tasks) {
            this.tasks.addAll(List.of(tasks));
            return this;
        }

        public ProjectBuilder begin(LocalDateTime begin) {
            this.begin = begin;
            return this;
        }

        public ProjectBuilder end(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public Project build() {
            return new Project(name, description, tags, tasks, begin, end);
        }
    }
}
