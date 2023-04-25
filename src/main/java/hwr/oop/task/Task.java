package hwr.oop.task;

import hwr.oop.project.Project;
import hwr.oop.tag.Tag;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Task implements TaskInterface {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Project project;
    private Set<Tag> tags;
    private final LocalDateTime dateTimeCreated;
    private LocalDateTime dateTimeDone;
    private LocalDateTime dateTimePlannedStart;
    private LocalDateTime dateTimePlannedEnd;
    private LocalDateTime dateTimeDeadline;

    protected Task(
            String title,
            String description,
            LocalDateTime dateTimeDeadline,
            TaskPriority priority,
            LocalDateTime dateTimePlannedStart,
            LocalDateTime dateTimePlannedEnd
    ) {
        this.title = title;
        this.description = description;
        this.dateTimeDeadline = dateTimeDeadline;
        this.priority = priority;
        this.dateTimePlannedStart = dateTimePlannedStart;
        this.dateTimePlannedEnd = dateTimePlannedEnd;
        this.status = TaskStatus.IN_TRAY;
        this.tags = new HashSet<>();
        this.dateTimeCreated = LocalDateTime.now();
    }

    // AREA: Interactions
    public void finishTask() {
        this.status = TaskStatus.DONE;
        this.dateTimeDone = LocalDateTime.now();
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

    public long getTimePlanned() {
        Duration difference = Duration.between(this.dateTimePlannedStart, this.dateTimePlannedEnd);
        return Math.abs(difference.toMinutes());
    }

    public long getTimeUntilDeadline() {
        Duration diff = Duration.between(LocalDateTime.now(), this.dateTimeDeadline);
        return diff.toMinutes();
    }

    public void toPreviousStatus() {
        if (this.status == TaskStatus.DONE) {
            this.status = TaskStatus.IN_PROGRESS;
            this.dateTimeDone = null;
        } else if (this.status == TaskStatus.IN_PROGRESS) {
            this.status = TaskStatus.BACKLOG;
        }
    }

    public void removeFromProject() {
        if (this.project != null) {
            this.project.removeTask(this);
            this.project = null;
            this.status = TaskStatus.IN_TRAY;
        }
    }

    public void changeProject(Project project) {
        if (this.project != null) {
            this.project.removeTask(this);
        }
        if (this.status == TaskStatus.IN_TRAY) {
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

    public LocalDateTime getDateTimeCreated() {
        return this.dateTimeCreated;
    }

    public LocalDateTime getDateTimeDone() {
        return this.dateTimeDone;
    }

    public LocalDateTime getDateTimePlannedStart() {
        return this.dateTimePlannedStart;
    }

    public LocalDateTime getDateTimePlannedEnd() {
        return this.dateTimePlannedEnd;
    }

    public LocalDateTime getDateTimeDeadline() {
        return this.dateTimeDeadline;
    }

    // AREA: Setters
    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changePriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void changePlannedDateTime(LocalDateTime startDate, LocalDateTime endDate) {
        this.dateTimePlannedStart = startDate;
        this.dateTimePlannedEnd = endDate;
    }

    public void changeDateTimeDeadline(LocalDateTime dateTimeDeadline) {
        this.dateTimeDeadline = dateTimeDeadline;
    }
}
