package hwr.oop.task;

import hwr.oop.project.Project;
import hwr.oop.tag.Tag;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Task implements TaskInterface {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Project project;
    private Set<Tag> tags;
    private final LocalDateTime dateCreated;
    private LocalDateTime dateDone;
    private LocalDateTime datePlannedStart;
    private LocalDateTime datePlannedEnd;
    private LocalDateTime deadline;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.IN_TRACE;
        this.priority = TaskPriority.UNDETERMINED;
        this.tags = new HashSet<>();
        this.dateCreated = LocalDateTime.now();
    }

    // AREA: Interactions
    public void finishTask() {
        this.status = TaskStatus.DONE;
        this.dateDone = LocalDateTime.now();
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public void toFurtherStatus() {
        if (this.status == TaskStatus.BACKLOG) this.status = TaskStatus.IN_PROGRESS;
    }

    public void toPreviousStatus() {
        if (this.status == TaskStatus.DONE) {
            this.status = TaskStatus.IN_PROGRESS;
            this.dateDone = null;
        } else if (this.status == TaskStatus.IN_PROGRESS) {
            this.status = TaskStatus.BACKLOG;
        }
    }

    public void removeFromProject() {
        if (this.project != null) {
            this.project.removeTask(this);
            this.project = null;
            this.status = TaskStatus.IN_TRACE;
        }
    }

    public void changeProject(Project project) {
        if (this.project != null) {
            this.project.removeTask(this);
        }
        if (this.status == TaskStatus.IN_TRACE) {
            this.status = TaskStatus.BACKLOG;
        }
        this.project = project;
        this.project.addTask(this);
    }

    // AREA: Getters
    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public TaskPriority getPriority() {
        return this.priority;
    }

    public Project getProject() {
        return this.project;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public LocalDateTime getDateDone() {
        return this.dateDone;
    }

    public LocalDateTime getDatePlannedStart() {
        return this.datePlannedStart;
    }

    public LocalDateTime getDatePlannedEnd() {
        return this.datePlannedEnd;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    // AREA: Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setPlannedDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            this.datePlannedStart = endDate;
            this.datePlannedEnd = startDate;
        } else {
            this.datePlannedStart = startDate;
            this.datePlannedEnd = endDate;
        }
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
