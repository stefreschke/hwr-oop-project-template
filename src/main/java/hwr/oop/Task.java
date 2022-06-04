package hwr.oop;

public class Task {
    private String taskName;
    private String date;
    private String deadline;

    public Task(String taskName, String date, String deadline) {
        this.taskName = taskName;
        this.date = date;
        this.deadline = deadline;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDate() {
        return date;
    }

    public String getDeadline() {
        return deadline;
    }
}
