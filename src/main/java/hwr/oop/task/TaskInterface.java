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
    LocalDateTime getDateTimePlannedStart();
    LocalDateTime getDateTimePlannedEnd();
    LocalDateTime getDateTimeDeadline();
    void changeTitle(String title);
    void changeDescription(String description);
    void changePriority(TaskPriority priority);
    void changePlannedDateTime(LocalDateTime startDate, LocalDateTime endDate);
    void changeDateTimeDeadline(LocalDateTime dateTimeDeadline);
    long getTimePlanned();
    long getTimeUntilDeadline();
    void changeProject(Project project);
    long getTimeOpen();
}
