package hwr.oop;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static hwr.oop.ConsoleColors.*;

public class ToDoItem {
    private String title;
    private String description;
    private String tag;
    private Priority priority;
    private Project project;
    private String createdAt;
    private State state;
    public ToDoItem (String title, String description, String tag, Priority priority, Project project) {
        this.title = WHITE_BOLD_BRIGHT + title + RESET;
        this.description = description;
        this.tag = CYAN_BOLD + tag + RESET;
        this.createdAt = LocalDateTime.now().toString();
        this.priority = priority;
        this.project = project;
        this.state = State.TODO;
    }
    void setTitle(String title) {
        this.title = WHITE_BOLD_BRIGHT + title + RESET;
    }
    void setDescription(String description) {
        this.description = description;
    }
    void setDone(boolean done) {
        this.state = State.DONE;
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
    public String getState() {
        return state.toString();
    }
    public String getStateEmoji() {
        switch (state) {
            case DONE:
                return "✅";
            case TODO:
                return "⏭️";
            case IN_PROGRESS:
                return "🏗️";
            case ON_HOLD:
                return "🕑";
            default:
                return "❓";
        }
    }
    public String getPriorityString() {
        switch (priority) {
            case LOW:
                return BLUE_BOLD + "LOW" + RESET;
            case MEDIUM:
                return YELLOW_BOLD + "MEDIUM" + RESET;
            case HIGH:
                return RED_BOLD + "HIGH" + RESET;
            default:
                return "❓";
        }
    }
    public boolean isDone() {
        return state == State.DONE;
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
        String stateSymbol = getStateEmoji() + ' ';
        String priority = getPriorityString();
        return  stateSymbol + title + '\n' +
                description + '\n' +
                "<" + tag + ">" + ' ' +
                priority;
    }
    public String getCreatedAt() {
        return createdAt;
    }

}
