package hwr.oop.task;

import hwr.oop.project.Project;
import hwr.oop.project.ProjectBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskSetterTest {
    private final TaskBuilder taskBuilder = new TaskBuilder();

    @Test
    void canSetTitle() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        task.changeTitle("New Title");
        String title = task.getTitle();
        assertThat(title).isEqualTo("New Title");
    }

    @Test
    void canSetDescription() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        task.changeDescription("New Description");
        String description = task.getDescription();
        assertThat(description).isEqualTo("New Description");
    }

    @ParameterizedTest
    @EnumSource(TaskPriority.class)
    void canSetPriority(TaskPriority priority) {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        task.changePriority(priority);
        TaskPriority taskPriority = task.getPriority();
        assertThat(taskPriority).isEqualTo(priority);
    }

    @Test
    void canSetDeadline() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        LocalDateTime testDateTime = LocalDateTime.now();
        task.changeDateTimeDeadline(testDateTime);
        LocalDateTime deadline = task.getDateTimeDeadline();
        assertThat(deadline).isEqualTo(testDateTime);
    }

    @Test
    void canChangeProject() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        final Project project = new ProjectBuilder().build();
        task.changeProject(project);
        Project taskProject = task.getProject();
        TaskStatus taskStatus = task.getStatus();
        assertThat(taskProject).isEqualTo(project);
        assertThat(taskStatus).isNotEqualTo(TaskStatus.IN_TRAY);
    }

    @Test
    void canChangeProjectWhenAssigned() {
        final Task task = this.taskBuilder
                .setTitle("Title")
                .setDescription("Description")
                .build();
        final Project project = new ProjectBuilder().build();
        final Project secondProject = new ProjectBuilder().build();
        task.changeProject(project);
        task.changeProject(secondProject);
        Project taskProject = task.getProject();
        TaskStatus taskStatus = task.getStatus();
        assertThat(taskProject).isEqualTo(secondProject);
        assertThat(taskStatus).isNotEqualTo(TaskStatus.IN_TRAY);
    }


    @Nested
    class CanSetPlannedDate {
        final TaskBuilder taskBuilder = new TaskBuilder();

        @Test
        void canSetPlannedDateInBuilder() {
            final LocalDateTime firstDateTime = LocalDateTime.now();
            final LocalDateTime secondDateTime = LocalDateTime.now().plus(100, ChronoUnit.MINUTES);
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .setDateTimePlanned(firstDateTime, secondDateTime)
                    .build();
            LocalDateTime startDate = task.getDateTimePlannedStart();
            LocalDateTime endDate = task.getDateTimePlannedEnd();
            assertThat(startDate).isEqualTo(firstDateTime);
            assertThat(endDate).isEqualTo(secondDateTime);
        }

        @Test
        void canSetPlannedDate() {
            final LocalDateTime firstDateTime = LocalDateTime.now();
            final LocalDateTime secondDateTime = LocalDateTime.now().plus(100, ChronoUnit.MINUTES);
            final Task task = this.taskBuilder
                    .setTitle("Title")
                    .setDescription("Description")
                    .build();
            task.changePlannedDateTime(firstDateTime, secondDateTime);
            LocalDateTime startDate = task.getDateTimePlannedStart();
            LocalDateTime endDate = task.getDateTimePlannedEnd();
            assertThat(startDate).isEqualTo(firstDateTime);
            assertThat(endDate).isEqualTo(secondDateTime);
        }
    }
}
