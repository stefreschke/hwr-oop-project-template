package hwr.oop;

public class Task {
    private String taskName;
    private String date;
    private String kanbanLabel;
    private String deadline;

    public Task(String taskName, String date, String kanbanLabel, String deadline) {
        this.taskName = taskName;
        this.date = date;
        this.kanbanLabel = kanbanLabel;
        this.deadline = deadline;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDate() {
        return date;
    }

    public String getKanbanLabel() {
        return kanbanLabel;
    }

    public String getDeadline() {
        return deadline;
    }
}
