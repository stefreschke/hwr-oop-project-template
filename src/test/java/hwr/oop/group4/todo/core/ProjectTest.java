package hwr.oop.group4.todo.core;

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

    @Test
    void  canAddTag(){
        final Tag tag = new Tag("tag1");
        final Project project = createProject();
        project.addTag(tag);

        assertThat(project.getTags()).contains(tag);
    }

    @Test
    void canBuildAddTag(){
        final Tag tag = new Tag("tag");
        final Project project = new Project.ProjectBuilder().addTag(tag).build();

        assertThat(project.getTags()).contains(tag);
        assertThat(project.getTags()).hasSize(1);
    }

    @Test
    void canBuildAddTags(){
        final Tag tag = new Tag("tag");
        final Tag tag2 = new Tag("tag2");
        final Project project = new Project.ProjectBuilder().addTag(tag, tag2).build();

        assertThat(project.getTags()).contains(tag, tag2);
        assertThat(project.getTags()).hasSize(2);
    }

    @Test
    void canBuildAddTask(){
        final Task task = new Task.TaskBuilder().build();
        final Project project = new Project.ProjectBuilder().addTask(task).build();

        assertThat(project.getTasks()).contains(task);
        assertThat(project.getTasks()).hasSize(1);
    }

    @Test
    void canBuildAddTasks(){
        final Task task = new Task.TaskBuilder().build();
        final Task task2 = new Task.TaskBuilder().name("Task2").build();
        final Project project = new Project.ProjectBuilder().addTasks(task, task2).build();

        assertThat(project.getTasks()).contains(task, task2);
        assertThat(project.getTasks()).hasSize(2);
    }
}
