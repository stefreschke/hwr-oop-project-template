package hwr.oop;

import java.time.LocalDate;

public class ToDoItem {

    public String title;
    public String description;
    public String tag;
    public boolean done;
    public Priority priority;

    public ToDoItem () {
        this.title = "New Item";
        this.description = "" + "\nCreated " + getLocalDate();
        this.tag = "";
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
    void setTag(String tag) {
        this.tag = tag;
    }

    void setDone(boolean done) {
        this.done = done;
    }

    void setPriority(Priority priority) {
        this.priority = priority;
    }


    // getter
    static String getLocalDate() {
        return LocalDate.now().toString();
    }
}
