package hwr.oop.group4.todo;

import hwr.oop.group4.todo.builder.TaskBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {
    @Test
    void buildDefaultTask() {
        final Task task = new TaskBuilder().build();

        assertThat(task.getDeadline()).isNull();
    }

    @Test
    void equalName() {
        final Task task = new TaskBuilder().build();

        assertThat("unnamed task").isEqualTo(task.getName());
    }

    @Test
    void getPriority() {
        final Task task = new TaskBuilder().build();

        assertThat(0).isEqualTo(task.getPriority());
    }

    @Test
    void getTags() {
        final Task task = new TaskBuilder().build();

        assertThat(0).isEqualTo(task.getTags().size());
    }

    @Test
    void getDescription() {
        final Task task = new TaskBuilder().build();

        assertThat("").isEqualTo(task.getDescription());
    }

    @Test
    void getStatus() {
        final Task task = new TaskBuilder().build();

        assertThat(Status.OPEN).isEqualTo(task.getStatus());
    }

    @Test
    void getNameFrom() {
        Task task = new TaskBuilder().setName("named task").build();

        assertThat(task.getName()).isEqualTo("named task");
    }

    @Test
    void getDescFrom() {
        Task task = new TaskBuilder().setDescription("desc").build();

        assertThat(task.getDescription()).isEqualTo("desc");
    }

    @Test
    void getPriorityFrom() {
        Task task = new TaskBuilder().setPriority(10).build();

        assertThat(task.getPriority()).isEqualTo(10);
    }

    @Test
    void canAddOneTag() {
        final Tag tag = new Tag("tagA");
        final Task task = new TaskBuilder().addTag(tag).build();

        assertThat(task.getTags()).contains(tag);
        assertThat(task.getTags().size()).isEqualTo(1);
    }

    @Test
    void canAddMoreTags() {
        final Tag tag = new Tag("tagA");
        final Tag tag2 = new Tag("tagB");
        final Task task = new TaskBuilder().addTags(tag, tag2).build();

        assertThat(task.getTags()).contains(tag);
        assertThat(task.getTags()).contains(tag2);
    }

    @Test
    void canGetTagsSize() {
        final Tag tag = new Tag("tagA");
        final Tag tag2 = new Tag("tagB");
        final Task task = new TaskBuilder().addTags(tag, tag2).build();

        assertThat(task.getTags().size()).isEqualTo(2);
    }

    @Test
    void getDeadline() {
        final LocalDateTime deadline = LocalDateTime.now().plusMonths(2);
        final Task task = new TaskBuilder().setDeadline(deadline).build();

        assertThat(task.getDeadline()).isEqualTo(deadline);
    }

    @Test
    void buildFromIdea() {
        final Idea idea = new Idea("Name from idea", "name from Description");
        final Task task = new TaskBuilder().fromIdea(idea).build();

        assertThat(task.getName()). isEqualTo("Name from idea");
        assertThat(task.getDescription()).isEqualTo("name from Description");
    }

    @Test
    void buildTaskInProject() {
        final Project project = new Project("project", "desc");
        final Task taskInProject = new TaskBuilder().setName("task1").setProject(project).build();

        assertThat(project.getTasks()).contains(taskInProject);

    }

    @Test
    void buildTaskNotInProject() {
        final Project project = new Project("project", "desc");
        final Task taskWithoutProject = new TaskBuilder().setName("task2").build();

        assertThat(project.getTasks()).doesNotContain(taskWithoutProject);
    }

    @Test
    void compareTwoSimpleTasks() {
        final Task defaultTask = new TaskBuilder().build();
        final Task defaultTask2 = new TaskBuilder().build();
        final Task dtask = new TaskBuilder().setName("name").build();

        assertThat(defaultTask).isEqualTo(defaultTask2);
        assertThat(defaultTask).isNotEqualTo(dtask);
    }

    @Test
    void compareStatusNotEqual() {
        final Task defaultTask = new TaskBuilder().build();
        final Task defaultTask2 = new TaskBuilder().build();
        defaultTask2.setStatus(Status.CLOSED);

        assertThat(defaultTask).isNotEqualTo(defaultTask2);
    }

    @Test
    void compareTwoComplexTasks() {
        final  Task task = new TaskBuilder().setName("Hallo!").build();
        final Task complexTask = new TaskBuilder()
                .setName("123")
                .setDescription("desc")
                .setPriority(10)
                .addTag(new Tag("1234"))
                .setDeadline(LocalDateTime.of(1970, 1, 1, 0, 0)).build();
        final Task complexTask2 = new TaskBuilder()
                .setName("123")
                .setDescription("desc")
                .setPriority(10)
                .addTag(new Tag("1234"))
                .setDeadline(LocalDateTime.of(1970, 1, 1, 0, 0)).build();

        assertThat(complexTask).isEqualTo(complexTask2);
        assertThat(complexTask).isNotEqualTo(task);
    }

}