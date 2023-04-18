package hwr.oop.task;

import hwr.oop.project.Project;
import hwr.oop.tag.Tag;

import java.time.LocalDateTime;
import java.util.Set;

public interface TaskInterface {
    void finishTask();
    void addTag(Tag tag);
    void removeTag(Tag tag);
    void toFurtherStatus();
    void toPreviousStatus();
    void removeFromProject();
    String getTitle();
    String getDescription();
    TaskStatus getStatus();
    TaskPriority getPriority();
    Project getProject();
    Set<Tag> getTags();
    LocalDateTime getDateTimeCreated();
    LocalDateTime getDateTimeDone();
    LocalDateTime getDatePlannedStart();
    LocalDateTime getDatePlannedEnd();
    LocalDateTime getDateTimeDeadline();
    void setTitle(String title);
    void setDescription(String description);
    void setPriority(TaskPriority priority);
    void setPlannedDateTime(LocalDateTime startDate, LocalDateTime endDate);
    void setDateTimeDeadline(LocalDateTime dateTimeDeadline);
}
