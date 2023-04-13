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
    LocalDateTime getDateCreated();
    LocalDateTime getDateDone();
    LocalDateTime getDatePlannedStart();
    LocalDateTime getDatePlannedEnd();
    LocalDateTime getDeadline();
    void setTitle(String title);
    void setDescription(String description);
    void setPriority(TaskPriority priority);
    void setPlannedDate(LocalDateTime startDate, LocalDateTime endDate);
    void setDeadline(LocalDateTime deadline);
    void setProject(Project project);
}
