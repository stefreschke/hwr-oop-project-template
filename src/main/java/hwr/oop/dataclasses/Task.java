package hwr.oop.dataclasses;

import java.util.Date;
import java.util.List;

public abstract class Task {
    public Task(Integer id, String title, String content, TaskState taskState, List<TaskTag> taskTagList, User creator, Date createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.taskState = taskState;
        this.taskTagList = taskTagList;
        this.creator = creator;
        this.createTime = createTime;
    }

    private Integer id;
    private String title;
    private String content;
    private TaskState taskState;
    private List<TaskTag> taskTagList;
    private User creator;
    private Date createTime;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }
}