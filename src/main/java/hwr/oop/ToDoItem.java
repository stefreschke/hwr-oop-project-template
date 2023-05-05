package hwr.oop;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static hwr.oop.ConsoleColors.*;

public class ToDoItem {
    private String title;
    private String description;
    private String tag;
    private boolean done;
    private Priority priority;
    private Project project;
    private String createdAt;

    public ToDoItem (String title, String description, String tag, boolean done, Priority priority, Project project) {
        this.title = WHITE_BOLD_BRIGHT + title + RESET;
        this.description = description;
        this.tag = CYAN_BOLD + tag + RESET;
        this.done = done;
        this.createdAt = LocalDateTime.now().toString();
        this.priority = priority;
        this.project = project;
    }
    void setTitle(String title) {
        this.title = WHITE_BOLD_BRIGHT + title + RESET;
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
    public String getProjectName() {
        return project.getTitle();
    }
    public void setCreatedAt(@org.jetbrains.annotations.NotNull LocalDateTime createdAt) {
        this.createdAt = createdAt.toString();
    }

    @Override
    public String toString() {
        String doneSymbol = this.done ? "✅ " : "❌ " ;
        String priority = (this.priority == Priority.LOW ? BLUE_BOLD : this.priority == Priority.MEDIUM ? YELLOW_BOLD : RED_BOLD) + this.priority + RESET;
        return  doneSymbol + title + '\n' +
                description + '\n' +
                "<" + tag + ">" + ' ' +
                priority;
    }

    public String getCreatedAt() {
        return createdAt;
    }

}
