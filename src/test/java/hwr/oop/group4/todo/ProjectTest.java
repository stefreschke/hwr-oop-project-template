package hwr.oop.group4.todo;

import hwr.oop.group4.todo.builder.TaskBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTest {

    @Test
    void createProject() {
        var beginAt = LocalDateTime.now();
        var endAt = LocalDateTime.now();
        Project project = new Project("myProject", "myDesc", new HashSet<>(), new HashSet<>(), beginAt, endAt);

        assertThat(project.getName()).isEqualTo("myProject");
        assertThat(project.getDescription()).isEqualTo("myDesc");
        assertThat(project.getTags().size()).isEqualTo(0);
        assertThat(project.getTasks().size()).isEqualTo(0);
        assertThat(project.getBegin()).isEqualTo(beginAt);
        assertThat(project.getEnd()).isEqualTo(endAt);
    }
}
