package hwr.oop.task;

import java.time.LocalDateTime;

public class TaskBuilder {
    private String title;
    private String description;
    private LocalDateTime dateTimeDeadline;
    private TaskPriority priority;
    private LocalDateTime dateTimePlannedStart;
    private LocalDateTime dateTimePlannedEnd;

    public TaskBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder setDateTimeDeadline(LocalDateTime dateTimeDeadline) {
        this.dateTimeDeadline = dateTimeDeadline;
        return this;
    }

    public TaskBuilder setPriority(TaskPriority priority) {
        this.priority = priority;
        return this;
    }

    public TaskBuilder setDateTimePlanned(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        this.dateTimePlannedStart = dateTimeStart;
        this.dateTimePlannedEnd = dateTimeEnd;
        return this;
    }

    public Task build() {
        if (this.priority == null) this.priority = TaskPriority.UNDETERMINED;
        return new Task(title, description, dateTimeDeadline, priority, dateTimePlannedStart, dateTimePlannedEnd);
    }
}