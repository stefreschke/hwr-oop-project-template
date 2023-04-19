package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectTest {
    private final LocalDateTime beginAt = LocalDateTime.of(1900, 10, 11, 20, 21);
    private final LocalDateTime endAt = LocalDateTime.of(2500, 12, 21, 22, 24);

    private Project createProject() {

        return new Project.ProjectBuilder()
                .name("myProject")
                .description("myDesc")
                .begin(beginAt)
                .end(endAt)
                .build();
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

        assertThat(project.getTags()).isEmpty();
    }

    @Test
    void canGetTasks() {
        Project project = createProject();

        assertThat(project.getTasks()).isEmpty();
    }

    @Test
    void canGetTime() {
        final Project project = createProject();

        assertThat(project.getBegin()).isEqualTo(beginAt);
        assertThat(project.getEnd()).isEqualTo(endAt);
    }
}
