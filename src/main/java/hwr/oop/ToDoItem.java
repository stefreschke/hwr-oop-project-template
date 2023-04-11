package hwr.oop;

public class ToDoItem {

    public String title;
    public String description;
    public boolean done;
    public Priority priority;

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
}
