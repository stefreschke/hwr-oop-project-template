package hwr.oop.todo;

import java.time.LocalDate;

public class Project {
    private final String title;
 /*
    private List<Task> task-list;
  */
    private LocalDate deadline;

    public Project(String title , LocalDate deadline) {
        this.title = title;
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
    public String getTitle() {
        return title;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }


}