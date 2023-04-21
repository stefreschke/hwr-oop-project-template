package hwr.oop.project;

import hwr.oop.task.Task;
import hwr.oop.task.TaskBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectTest {
    private final ProjectBuilder projectBuilder = new ProjectBuilder();

    @Test
    void canBuild() {
        final String title = "Title";
        final String description = "Description";
        final LocalDateTime deadline = LocalDateTime.now();
        final Project project = projectBuilder
                .setTitle(title)
                .setDescription(description)
                .setDeadline(deadline)
                .build();
        assertThat(project).isInstanceOf(Project.class);
        assertThat(project.getTitle()).isEqualTo(title);
        assertThat(project.getDescription()).isEqualTo(description);
        assertThat(project.getDeadline()).isEqualTo(deadline);
        assertThat(project.getDateTimeCreated()).isNotNull();
    }

    @Test
    void canChangeTitle() {
        final String title = "Title";
        final Project project = projectBuilder.build();
        project.changeTitle(title);
        assertThat(project.getTitle()).isEqualTo(title);
    }

    @Test
    void canChangeDescription() {
        final String description = "Description";
        final Project project = projectBuilder.build();
        project.changeDescription(description);
        assertThat(project.getDescription()).isEqualTo(description);
    }

    @Test
    void canChangeDeadline() {
        final LocalDateTime deadline = LocalDateTime.now();
        final Project project = projectBuilder.build();
        project.changeDeadline(deadline);
        assertThat(project.getDeadline()).isEqualTo(deadline);
    }

    @Test
    void canAddTasks() {
        final Project project = projectBuilder.build();
        final Task task = new TaskBuilder().build();
        project.addTask(task);
        project.addTask(task);
        assertThat(project.getTasks()).containsExactly(task);
    }
    @Test
    void canRemoveTasks() {
        final Project project = projectBuilder.build();
        final Task task = new TaskBuilder().build();
        final Task secondTask = new TaskBuilder().build();
        project.addTask(task);
        project.addTask(secondTask);
        project.removeTask(secondTask);
        assertThat(project.getTasks()).containsExactly(task);
    }
}
