package hwr.oop;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static hwr.oop.util.ConsoleColors.*;

public class ToDoItem {
    private String title;
    private String description;

    private Bucket bucket;
    private Priority priority;
    private String createdAt;
    private State state;
    private LocalDate dueDate;

    public ToDoItem (String title, String description, Bucket bucket, Priority priority, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.bucket = bucket;
        this.createdAt = LocalDateTime.now().toString();
        this.priority = priority;
        this.state = State.TODO;
        this.dueDate = dueDate;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDone() {
        this.state = State.DONE;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt.toString();
    }
    public String getTitle() {
        return title;
    }
    public Bucket getBucket() {
        return this.bucket;
    }
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return state == State.DONE;
    }
    public Priority getPriority() {
        return priority;
    }
    public String getState() {
        return state.toString();
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public String getCreatedAt() {
        return createdAt;
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
    @Override
    public String toString() {
        String stateSymbol = getStateEmoji() + ' ';
        String priorityString = getPriorityString();
        return  stateSymbol + title + '\n' +
                description + '\n' +
                "<" +  CYAN_BOLD + bucket.getBucketName() + RESET + ">" +
                ' ' + priorityString + ' ' + dueDate;
    }

    public void promote() {
        this.state = state.nextState();
    }
    public void demote() {
        this.state = state.previousState();
    }
    public void hold(){
        this.state = state.hold();
    }
}
