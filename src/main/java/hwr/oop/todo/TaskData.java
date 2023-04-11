package hwr.oop.todo;

public class TaskData {
    private String title;
    private String description;
    private TaskState state;

    public TaskData(String title, String description){
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
    }

    public TaskData(String title) {
        this(title, "");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}
