package hwr.oop.project;

import java.time.LocalDateTime;

public class ProjectBuilder {
    private String title;
    private String description;
    private LocalDateTime deadline;

    public ProjectBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProjectBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectBuilder setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }

    public Project build() {
        return new Project(title, description, deadline);
    }
}