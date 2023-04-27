package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {
    @Test
    void buildDefaultTask() {
        final Task task = new Task.TaskBuilder().build();

        assertThat(task.getDeadline()).isNull();
    }

    @Test
    void equalName() {
        final Task task = new Task.TaskBuilder().build();

        assertThat("unnamed task").isEqualTo(task.getName());
    }

    @Test
    void getPriority() {
        final Task task = new Task.TaskBuilder().build();

        assertThat(0).isEqualTo(task.getPriority());
    }

    @Test
    void getTags() {
        final Task task = new Task.TaskBuilder().build();

        assertThat(0).isEqualTo(task.getTags().size());
    }

    @Test
    void getDescription() {
        final Task task = new Task.TaskBuilder().build();

        assertThat("").isEqualTo(task.getDescription());
    }

    @Test
    void getStatus() {
        final Task task = new Task.TaskBuilder().build();

        assertThat(Status.OPEN).isEqualTo(task.getStatus());
    }

    @Test
    void getNameFrom() {
        Task task = new Task.TaskBuilder().name("named task").build();

        assertThat(task.getName()).isEqualTo("named task");
    }

    @Test
    void getDescFrom() {
        Task task = new Task.TaskBuilder().description("desc").build();

        assertThat(task.getDescription()).isEqualTo("desc");
    }

    @Test
    void getPriorityFrom() {
        Task task = new Task.TaskBuilder().priority(10).build();

        assertThat(task.getPriority()).isEqualTo(10);
    }

    @Test
    void canAddOneTag() {
        final Tag tag = new Tag("tagA");
        final Task task = new Task.TaskBuilder().addTag(tag).build();

        assertThat(task.getTags()).contains(tag);
        assertThat(task.getTags().size()).isEqualTo(1);
    }

    @Test
    void canAddMoreTags() {
        final Tag tag = new Tag("tagA");
        final Tag tag2 = new Tag("tagB");
        final Tag tag3 = new Tag("tagC");
        final Task task = new Task.TaskBuilder().addTags(tag, tag2).build();
        task.addTag(tag3);

        assertThat(task.getTags()).contains(tag, tag2, tag3);
    }

    @Test
    void canGetTagsSize() {
        final Tag tag = new Tag("tagA");
        final Tag tag2 = new Tag("tagB");
        final Task task = new Task.TaskBuilder().addTags(tag, tag2).build();

        assertThat(task.getTags().size()).isEqualTo(2);
    }

    @Test
    void getDeadline() {
        final LocalDateTime deadline = LocalDateTime.now().plusMonths(2);
        final Task task = new Task.TaskBuilder().deadline(deadline).build();

        assertThat(task.getDeadline()).isEqualTo(deadline);
    }

    @Test
    void buildFromIdea() {
        final Idea idea = new Idea("Name from idea", "name from Description");
        final Task task = new Task.TaskBuilder().fromIdea(idea).build();

        assertThat(task.getName()).isEqualTo("Name from idea");
        assertThat(task.getDescription()).isEqualTo("name from Description");
    }

    @Test
    void buildTaskInProject() {
        final Project project = new Project.ProjectBuilder().name("project").description("desc").build();
        final Task taskInProject = new Task.TaskBuilder().name("task1").project(project).build();

        assertThat(project.getTasks()).contains(taskInProject);

    }

    @Test
    void buildTaskNotInProject() {
        final Project project = new Project.ProjectBuilder().name("project").description("desc").build();
        final Task taskWithoutProject = new Task.TaskBuilder().name("task2").build();

        assertThat(project.getTasks()).doesNotContain(taskWithoutProject);
    }

    @Test
    void compareTwoSimpleTasks() {
        final Task defaultTask = new Task.TaskBuilder().build();
        final Task defaultTask2 = new Task.TaskBuilder().build();
        final Task dtask = new Task.TaskBuilder().name("name").build();

        assertThat(defaultTask).isEqualTo(defaultTask2);
        assertThat(defaultTask).isNotEqualTo(dtask);
    }

    @Test
    void compareStatusNotEqual() {
        final Task defaultTask = new Task.TaskBuilder().build();
        final Task defaultTask2 = new Task.TaskBuilder().build();
        defaultTask2.closed();

        assertThat(defaultTask).isNotEqualTo(defaultTask2);
    }

    @Test
    void addStatusOpen(){
        final Task task = new Task.TaskBuilder().build();
        task.open();

        assertThat(task.getStatus()).isEqualTo(Status.OPEN);
    }
    @Test
    void addStatusInProgress(){
        final Task task = new Task.TaskBuilder().build();
        task.inProgress();

        assertThat(task.getStatus()).isEqualTo(Status.IN_PROGRESS);
    }

    @Test
    void addStatusClosed(){
        final Task task = new Task.TaskBuilder().build();
        task.closed();

        assertThat(task.getStatus()).isEqualTo(Status.CLOSED);
    }

    @Test
    void compareTwoComplexTasks() {
        final Task task = new Task.TaskBuilder().name("Hallo!").build();
        final Task complexTask = new Task.TaskBuilder()
                .name("123")
                .description("desc")
                .priority(10)
                .addTag(new Tag("1234"))
                .deadline(LocalDateTime.of(1970, 1, 1, 0, 0))
                .build();
        final Task complexTask2 = new Task.TaskBuilder()
                .name("123")
                .description("desc")
                .priority(10)
                .addTag(new Tag("1234"))
                .deadline(LocalDateTime.of(1970, 1, 1, 0, 0))
                .build();

        assertThat(complexTask).isEqualTo(complexTask2);
        assertThat(complexTask).isNotEqualTo(task);
    }
}
