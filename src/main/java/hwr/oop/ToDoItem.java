package hwr.oop;

import java.time.LocalDateTime;

import static hwr.oop.util.ConsoleColors.*;

public class ToDoItem {
    private String title;
    private String description;

    private Bucket bucket;
    private Priority priority;
    private String createdAt;
    private State state;
    public ToDoItem (String title, String description, String bucket, Priority priority) {
        this.title = title;
        this.description = description;
        this.bucket = new Bucket(bucket);
        this.createdAt = LocalDateTime.now().toString();
        this.priority = priority;
        this.state = State.TODO;
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
    public void setBucket(String bucket) {
        this.bucket.setBucketName(bucket);
    }
    public String getTitle() {
        return title;
    }
    public String getBucket() {
        return bucket.getBucketName();
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
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt.toString();
    }
    @Override
    public String toString() {
        String stateSymbol = getStateEmoji() + ' ';
        String priorityString = getPriorityString();
        return  stateSymbol + title + '\n' +
                description + '\n' +
                "<" +  CYAN_BOLD + bucket.getBucketName() + RESET + ">" +
                ' ' + priorityString;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void promote() {
        switch (state) {
            case TODO:
            case ON_HOLD:
                state = State.IN_PROGRESS;
                break;
            case IN_PROGRESS:
                state = State.DONE;
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
            case ON_HOLD:
            case DONE:
                break;
            case IN_PROGRESS:
                state = State.ON_HOLD;
                break;
        }
    }


}
