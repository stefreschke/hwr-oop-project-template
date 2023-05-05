package hwr.oop;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ToDoItem {
    private final int id;
    private String title;
    private String description;
    private String tag;
    private boolean done;
    private Priority priority;
    private Project project;
    private String createdAt;

    public ToDoItem (int id, String title, String description, String tag, boolean done, Priority priority, Project project) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now().toString();
        this.tag = tag;
        this.done = done;
        this.priority = priority;
        this.project = project;
    }
    void setTitle(String title) {
        this.title = title;
    }
    void setDescription(String description) {
        this.description = description;
    }
    void setDone(boolean done) {
        this.done = done;
    }
    void setPriority(Priority priority) {
        this.priority = priority;
    }
    void setTag(String tag) {
        this.tag = tag;
    }
    void setProjectName(String project) {
        this.project.setTitle(project);
    }
    static @NotNull String getLocalDate() {
        return LocalDate.now().toString();
    }
    public String getTitle() {
        return title;
    }
    public String getTag() {
        return tag;
    }
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return done;
    }
    public Priority getPriority() {
        return priority;
    }
    public int getId() {
        return id;
    }
    public String getProjectName() {
        return project.getTitle();
    }
    public void setCreatedAt(@org.jetbrains.annotations.NotNull LocalDateTime createdAt) {
        this.createdAt = createdAt.toString();
    }

    @Override
    public String toString() {
        String doneSymbol = this.done ? "✅ " : "❌ " ;
        return  doneSymbol + title + '\n' +
                description + '\n' +
                "<" + tag + ">" + ' ' +
                priority + '\n';
    }

    public String getCreatedAt() {
        return createdAt;
    }

}
