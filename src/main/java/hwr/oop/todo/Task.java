package hwr.oop.todo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Task {
    public String title;
    public ArrayList<TaskTag> tags;
    public String description;
    public String deadline;
    public TaskStatus status;
    public TaskPriority priority;
    public Task(String title, ArrayList<TaskTag> tags, String description, String deadline, TaskStatus status, TaskPriority priority) {
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;

    }
}
