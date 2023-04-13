package hwr.oop.task;

import hwr.oop.project.Project;
import hwr.oop.tag.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskMethodTest {

    @Test
    void canAddTags() {
        final Task task = new Task("Title", "Description");
        final Tag tag = new Tag();
        task.addTag(tag);
        task.addTag(tag);
        Set<Tag> tags = task.getTags();
        assertThat(tags).containsExactly(tag);
        final Tag secondTag = new Tag();
        task.addTag(secondTag);
        tags = task.getTags();
        assertThat(tags).containsOnly(tag, secondTag);
    }

    @Test
    void canRemoveTags() {
        final Task task = new Task("Title", "Description");
        final Tag tag = new Tag();
        final Tag secondTag = new Tag();
        task.addTag(tag);
        task.addTag(secondTag);
        task.removeTag(secondTag);
        Set<Tag> tags = task.getTags();
        assertThat(tags).containsExactly(tag);
    }

    @Test
    void canFinishTask() {
        final Task task = new Task("Title", "Description");
        task.finishTask();
        TaskStatus status = task.getStatus();
        LocalDateTime dateDone = task.getDateDone();
        assertThat(status).isEqualTo(TaskStatus.DONE);
        assertThat(dateDone).isNotNull();
    }

    @Test
    void canSetToFurtherStatus() {
        final Task task = new Task("Title", "Description");
        final Project project = new Project("Project", LocalDateTime.now(), LocalDate.now());
        task.setProject(project);
        task.toFurtherStatus();
        TaskStatus status = task.getStatus();
        assertThat(status).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Test
    void canSetToPreviousStatus() {
        final Task task = new Task("Title", "Description");
        final Project project = new Project("Project", LocalDateTime.now(), LocalDate.now());
        task.setProject(project);
        task.finishTask();
        task.toPreviousStatus();
        TaskStatus status = task.getStatus();
        LocalDateTime dateDone = task.getDateDone();
        assertThat(status).isEqualTo(TaskStatus.IN_PROGRESS);
        assertThat(dateDone).isNull();
        task.toPreviousStatus();
        status = task.getStatus();
        assertThat(status).isEqualTo(TaskStatus.BACKLOG);
    }

    @Test
    void canRemoveTaskFromProject() {
        final Task task = new Task("Title", "Description");
        final Project project = new Project("Project", LocalDateTime.now(), LocalDate.now());
        task.setProject(project);
        task.removeFromProject();
        Project taskProject = task.getProject();
        TaskStatus taskStatus = task.getStatus();
        assertThat(taskProject).isNull();
        assertThat(taskStatus).isEqualTo(TaskStatus.IN_TRACE);
    }
}
