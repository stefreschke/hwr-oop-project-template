package hwr.oop.dataclasses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Task {
    public Task(Integer id, String title, String content, TaskState taskState, List<TaskTag> taskTagList, User creator,
                LocalDateTime deadline) {
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
    private final LocalDateTime deadline;

    public Optional<LocalDateTime> getDeadline() {
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

    //change TaskState to BACKLOG
    public void resetTask() {
        if (taskState == TaskState.IN_PROGRESS) {
            taskState = TaskState.BACKLOG;
        } else {
            throw new TaskStateException("task can't be reset with taskState" + taskState.name());
        }
    }

    //change TaskState to IN_PROGRESS
    public void startTask() {
        if (taskState == TaskState.BACKLOG || taskState == TaskState.IN_REVIEW || taskState == TaskState.DONE) {
            taskState = TaskState.IN_PROGRESS;
        } else {
            throw new TaskStateException("task can't be started with taskState" + taskState.name());
        }
    }

    //change TaskState to IN_REVIEW
    public void reviewTask() {
        if (taskState == TaskState.IN_PROGRESS) {
            taskState = TaskState.IN_REVIEW;
        } else {
            throw new TaskStateException("task can't be finished with taskState" + taskState.name());
        }
    }

    //change TaskState to DONE
    public void completeTask() {
        if (taskState == TaskState.IN_REVIEW) {
            taskState = TaskState.DONE;
        } else {
            throw new TaskStateException("task can't be completed with taskState" + taskState.name());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return (Objects.equals(((Task) obj).id, this.id));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, taskState, taskTagList, creator, deadline);
    }
}
