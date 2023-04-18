package hwr.oop.task;

import hwr.oop.project.Project;
import hwr.oop.tag.Tag;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskMethodTest {
    private final TaskBuilder taskBuilder = new TaskBuilder();

    @Nested
    class CanAddTags {
        private final TaskBuilder taskBuilder = new TaskBuilder();
        @Test
        void canAddOneTag() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Tag tag = new Tag();
            task.addTag(tag);
            Set<Tag> tags = task.getTags();
            assertThat(tags).containsExactly(tag);
        }

        @Test
        void onlyAddsOneTagOfTwoSameToSet() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Tag tag = new Tag();
            task.addTag(tag);
            task.addTag(tag);
            Set<Tag> tags = task.getTags();
            assertThat(tags).containsExactly(tag);
        }

        @Test
        void canAddMultipleTags() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Tag tag = new Tag();
            final Tag secondTag = new Tag();
            task.addTag(tag);
            task.addTag(secondTag);
            Set<Tag> tags = task.getTags();
            assertThat(tags).containsOnly(tag, secondTag);
        }
    }

    @Test
    void canRemoveTags() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
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
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        task.finishTask();
        TaskStatus status = task.getStatus();
        LocalDateTime dateDone = task.getDateTimeDone();
        assertThat(status).isEqualTo(TaskStatus.DONE);
        assertThat(dateDone).isNotNull();
    }

    @Test
    void canSetToFurtherStatus() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        final Project project = new Project("Project", LocalDateTime.now(), LocalDate.now());
        task.changeProject(project);
        task.toFurtherStatus();
        TaskStatus status = task.getStatus();
        assertThat(status).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Nested
    class CanChangeStatus {
        private final TaskBuilder taskBuilder = new TaskBuilder();
        @Test
        void canSetToPreviousStatusFromDone() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Project project = new Project("Project", LocalDateTime.now(), LocalDate.now());
            task.changeProject(project);
            task.finishTask();
            task.toPreviousStatus();
            TaskStatus status = task.getStatus();
            LocalDateTime dateDone = task.getDateTimeDone();
            assertThat(status).isEqualTo(TaskStatus.IN_PROGRESS);
            assertThat(dateDone).isNull();
        }

        @Test
        void canSetToPreviousStatusFromInProgress() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Project project = new Project("Project", LocalDateTime.now(), LocalDate.now());
            task.changeProject(project);
            task.toFurtherStatus();
            task.toPreviousStatus();
            TaskStatus status = task.getStatus();
            assertThat(status).isEqualTo(TaskStatus.BACKLOG);
        }
    }

    @Test
    void canRemoveTaskFromProject() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        final Project project = new Project("Project", LocalDateTime.now(), LocalDate.now());
        task.changeProject(project);
        task.removeFromProject();
        Project taskProject = task.getProject();
        TaskStatus taskStatus = task.getStatus();
        assertThat(taskProject).isNull();
        assertThat(taskStatus).isEqualTo(TaskStatus.IN_TRAY);
    }
}
