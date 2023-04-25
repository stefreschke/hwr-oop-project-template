package hwr.oop.dataclasses;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Task {
    public Task(Integer id, String title, String content, TaskState taskState, List<TaskTag> taskTagList, User creator,
                LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.taskState = taskState;
        this.taskTagList = taskTagList;
        this.creator = creator;
        this.deadline = deadline;
    }

    private final Integer id;
    private final String title;
    private final String content;
    private TaskState taskState;
    private final List<TaskTag> taskTagList;
    private final User creator;
    private final LocalDate deadline;

    public Optional<LocalDate> getDeadline() {
        return Optional.ofNullable(deadline);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public List<TaskTag> getTaskTagList() {
        return taskTagList;
    }

    public User getCreator() {
        return creator;
    }

    public void completeTask() {
        if (taskState == TaskState.IN_PROGRESS || taskState == TaskState.IN_REVIEW) {
            taskState = TaskState.DONE;
        } else {
            throw new TaskStateException("task can't be completed with taskState" + taskState.name());
        }
    }
}