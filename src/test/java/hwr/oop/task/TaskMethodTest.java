package hwr.oop.task;

import hwr.oop.project.Project;
import hwr.oop.project.ProjectBuilder;
import hwr.oop.tag.Tag;
import hwr.oop.tag.TagBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskMethodTest {
    private final TaskBuilder taskBuilder = new TaskBuilder();

    @Nested
    @DisplayName("Nested -> Can add tags.")
    class CanAddTags {
        private final TaskBuilder taskBuilder = new TaskBuilder();
        @Test
        @DisplayName("One tag can be added to Tag set.")
        void canAddOneTag() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Tag tag = new TagBuilder().setTitle("Title").build();
            task.addTag(tag);
            Set<Tag> tags = task.getTags();
            assertThat(tags).containsExactly(tag);
        }

        @Test
        @DisplayName("Only one tag added to set, when same tag object is added.")
        void onlyAddsOneTagOfTwoSameToSet() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Tag tag = new TagBuilder().setTitle("Title").build();
            task.addTag(tag);
            task.addTag(tag);
            Set<Tag> tags = task.getTags();
            assertThat(tags).containsExactly(tag);
        }

        @Test
        @DisplayName("Multiple distinct tags can be added.")
        void canAddMultipleTags() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Tag tag = new TagBuilder().setTitle("Title").build();
            final Tag secondTag = new TagBuilder().setTitle("Title").build();
            task.addTag(tag);
            task.addTag(secondTag);
            Set<Tag> tags = task.getTags();
            assertThat(tags).containsOnly(tag, secondTag);
        }
    }

    @Test
    @DisplayName("Tags can be removed from the set.")
    void canRemoveTags() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        final Tag tag = new TagBuilder().setTitle("Title").build();
        final Tag secondTag = new TagBuilder().setTitle("Title").build();
        task.addTag(tag);
        task.addTag(secondTag);
        task.removeTag(secondTag);
        Set<Tag> tags = task.getTags();
        assertThat(tags).containsExactly(tag);
    }

    @Test
    @DisplayName("Can finish a task.")
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
    @DisplayName("Can forward status to further status.")
    void canSetToFurtherStatus() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        final Project project = new ProjectBuilder().build();
        task.changeProject(project);
        task.toFurtherStatus();
        TaskStatus status = task.getStatus();
        assertThat(status).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Nested
    @DisplayName("Nested -> Can change task status.")
    class CanChangeStatus {
        private final TaskBuilder taskBuilder = new TaskBuilder();
        @Test
        @DisplayName("Can change status to previous from status DONE.")
        void canSetToPreviousStatusFromDone() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Project project = new ProjectBuilder().build();
            task.changeProject(project);
            task.finishTask();
            task.toPreviousStatus();
            TaskStatus status = task.getStatus();
            LocalDateTime dateDone = task.getDateTimeDone();
            assertThat(status).isEqualTo(TaskStatus.IN_PROGRESS);
            assertThat(dateDone).isNull();
        }

        @Test
        @DisplayName("Can change status to previous from status IN_PROGRESS.")
        void canSetToPreviousStatusFromInProgress() {
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            final Project project = new ProjectBuilder().build();
            task.changeProject(project);
            task.toFurtherStatus();
            task.toPreviousStatus();
            TaskStatus status = task.getStatus();
            assertThat(status).isEqualTo(TaskStatus.BACKLOG);
        }
    }

    @Test
    @DisplayName("Can remove task from a project.")
    void canRemoveTaskFromProject() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        final Project project = new ProjectBuilder().build();
        task.changeProject(project);
        task.removeFromProject();
        Project taskProject = task.getProject();
        TaskStatus taskStatus = task.getStatus();
        assertThat(taskProject).isNull();
        assertThat(taskStatus).isEqualTo(TaskStatus.IN_TRAY);
    }

    @Test
    @DisplayName("Can get time left between now and deadline.")
    void canGetTimeUntilDeadline() {
        final Task task = this.taskBuilder
                .setDateTimeDeadline(LocalDateTime.now().plus(2, ChronoUnit.HOURS))
                .build();
        Duration durationDiff = Duration.between(LocalDateTime.now(), task.getDateTimeDeadline());
        long difference = durationDiff.toMinutes();
        assertThat(task.getTimeUntilDeadline()).isEqualTo(difference);
    }

    @Test
    @DisplayName("Can get planned time.")
    void canGetTimeBetweenPlannedDates() {
        final Task task = this.taskBuilder
                .setDateTimePlanned(LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.DAYS))
                .build();
        Duration durationDiff = Duration.between(task.getDateTimePlannedStart(), task.getDateTimePlannedEnd());
        long difference = Math.abs(durationDiff.toMinutes());
        assertThat(task.getTimePlanned()).isEqualTo(difference);
    }

    @Test
    @DisplayName("Can get time open.")
    void canGetTimeOpen() {
        final Task task = this.taskBuilder.build();
        Duration durationDiff = Duration.between(LocalDateTime.now(), task.getDateTimeCreated());
        long difference = Math.abs(durationDiff.toMinutes());
        assertThat(task.getTimeOpen()).isEqualTo(difference);
    }

    @Test
    @DisplayName("Can get time open when done.")
    void canGetTimeOpenWhenDone() {
        final Task task = this.taskBuilder.build();
        task.finishTask();
        Duration durationDiff = Duration.between(task.getDateTimeDone(), task.getDateTimeCreated());
        long difference = Math.abs(durationDiff.toMinutes());
        assertThat(task.getTimeOpen()).isEqualTo(difference);
    }
}
