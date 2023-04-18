package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectTest {

    private Project createProject() {
        var beginAt = LocalDateTime.now();
        var endAt = LocalDateTime.now();

        return new Project("myProject", "myDesc", new HashSet<>(), new HashSet<>(), beginAt, endAt);
    }

    @Test
    void canGetName() {
        Project project = createProject();

        assertThat(project.getName()).isEqualTo("myProject");
    }

    @Test
    void canGetDescription() {
        Project project = createProject();

        assertThat(project.getDescription()).isEqualTo("myDesc");
    }

    @Test
    void canGetTag() {
        Project project = createProject();

        assertThat(project.getTags().size()).isEqualTo(0);
    }

    @Test
    void canGetTasks() {
        Project project = createProject();

        assertThat(project.getTasks().size()).isEqualTo(0);
    }

    @Test
    void canGetTime() {
        var beginAt = LocalDateTime.now();
        var endAt = LocalDateTime.now();
        Project project = new Project("myProject", "myDesc", new HashSet<>(), new HashSet<>(), beginAt, endAt);

        assertThat(project.getBegin()).isEqualTo(beginAt);
        assertThat(project.getEnd()).isEqualTo(endAt);
    }
}
