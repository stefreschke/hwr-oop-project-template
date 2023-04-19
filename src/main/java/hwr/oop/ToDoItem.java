package hwr.oop;

import java.time.LocalDate;

public class ToDoItem {
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public boolean isDone() {
        return done;
    }

    public Priority getPriority() {
        return priority;
    }

    private final int id;
    private final String title;
    private final String description;
    private final String tag;
    private final boolean done;
    private final Priority priority;

    public ToDoItem (int id, String title, String description, String tag, boolean done, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description + "\nCreated " + getLocalDate();
        this.tag = tag;
        this.done = done;
        this.priority = priority;
    }

    // setter

    static String getLocalDate() {
        return LocalDate.now().toString();
    }
}
