package hwr.oop.todo;

public class Project {
    private final String title;
 /*
    private List<Task> task-list;
  */
    private Status status;
    private String deadline;

    public Project(String title , String condition, String deadline) {
        this.title = title;
        this.status = new Status(condition);
        // this.task-list = null;
        this.deadline = deadline;
    }
/*
    public addTask(Task task) {
        this.task-list.add(task);
    }

    public getTasklist() {
        return this.task-list;
    }

    public getLastTask() {
        int pos = this.task-list.size();
        return this.task-list.get(pos-1);
    }
*/
    public void setStatus(String status) {   // In Status enthalten, darum löschen?
        this.status = new Status(status);
    }

    public String getStatus() {                // In Status enthalten, darum löschen?
        return this.status.getCondition();
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }
}
