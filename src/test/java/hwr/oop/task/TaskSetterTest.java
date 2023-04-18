package hwr.oop.task;

import hwr.oop.project.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskSetterTest {

    @Test
    void canSetTitle() {
        final Task task = new Task("Title", "Description");
        task.setTitle("New Title");
        String title = task.getTitle();
        assertThat(title).isEqualTo("New Title");
    }

    @Test
    void canSetDescription() {
        final Task task = new Task("Title", "Description");
        task.setDescription("New Description");
        String description = task.getDescription();
        assertThat(description).isEqualTo("New Description");
    }

    @ParameterizedTest
    @EnumSource(TaskPriority.class)
    void canSetPriority(TaskPriority priority) {
        final Task task = new Task("Title", "Description");
        task.setPriority(priority);
        TaskPriority taskPriority = task.getPriority();
        assertThat(taskPriority).isEqualTo(priority);
    }

    @Test
    void canSetDeadline() {
        final Task task = new Task("Title", "Description");
        LocalDateTime testDateTime = LocalDateTime.now();
        task.setDateTimeDeadline(testDateTime);
        LocalDateTime deadline = task.getDateTimeDeadline();
        assertThat(deadline).isEqualTo(testDateTime);
    }

    @Test
    void canChangeProject() {
        final Task task = new Task("Title", "Description");
        final Project project = new Project("Name", LocalDateTime.now(), LocalDate.now());
        task.changeProject(project);
        Project taskProject = task.getProject();
        TaskStatus taskStatus = task.getStatus();
        assertThat(taskProject).isEqualTo(project);
        assertThat(taskStatus).isNotEqualTo(TaskStatus.IN_TRAY);
    }

    @Test
    void canSetPlannedDate() {
        final Task task = new Task("Title", "Description");
        final LocalDateTime firstDateTime = LocalDateTime.now();
        final LocalDateTime secondDateTime = LocalDateTime.now().plus(100, ChronoUnit.MINUTES);
        task.setPlannedDateTime(firstDateTime, secondDateTime);
        LocalDateTime startDate = task.getDateTimePlannedStart();
        LocalDateTime endDate = task.getDateTimePlannedEnd();
        assertThat(startDate).isEqualTo(firstDateTime);
        assertThat(endDate).isEqualTo(secondDateTime);
    }
}
