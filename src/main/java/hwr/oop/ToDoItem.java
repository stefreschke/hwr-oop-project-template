package hwr.oop;

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
        this.title = title;
        this.description = description;
        this.tag = CYAN_BOLD + tag + RESET;
        this.createdAt = LocalDateTime.now().toString();
        this.priority = priority;
        this.project = project;
        this.state = State.TODO;
    }
    void setTitle(String title) {
        this.title = title;
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
    static String getLocalDate() {
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
        try {
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
        } catch (Exception e) {
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
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt.toString();
    }
    @Override
    public String toString() {
        String stateSymbol = getStateEmoji() + ' ';
        String priorityString = getPriorityString();
        return  stateSymbol + title + '\n' +
                description + '\n' +
                "<" + tag + ">" + ' ' +
                priorityString;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void promote() {
        switch (state) {
            case TODO:
                state = State.IN_PROGRESS;
                break;
            case IN_PROGRESS:
                state = State.DONE;
                break;
            case ON_HOLD:
                state = State.IN_PROGRESS;
                break;
            case DONE:
                break;
        }
    }
    public void demote() {
        switch (state) {
            case TODO:
                break;
            case IN_PROGRESS:
                state = State.TODO;
                break;
            case ON_HOLD:
                state = State.TODO;
                break;
            case DONE:
                state = State.IN_PROGRESS;
                break;
        }
    }

    public void hold() {
        switch (state) {
            case TODO:
                break;
            case IN_PROGRESS:
                state = State.ON_HOLD;
                break;
            case ON_HOLD:
                break;
            case DONE:
                break;
        }
    }


}
