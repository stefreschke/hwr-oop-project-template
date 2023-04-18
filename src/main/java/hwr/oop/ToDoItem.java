package hwr.oop;

public class ToDoItem {

    private String title;
    private String description;
    private boolean done;
    private Priority priority;

    public ToDoItem () {
        this.title = "New Item";
        this.description = "";
        this.done = false;
        this.priority = Priority.LOW;
    }

    // setter
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

    // getter
    public String getTitle() {
        return title;
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
}
