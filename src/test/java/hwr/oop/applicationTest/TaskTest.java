package hwr.oop.applicationTest;

import hwr.oop.application.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class TaskTest {

    @Test
    void getAttributes() {
        UUID id = UUID.randomUUID();
        String title = "taskTitle";
        String content = "taskContent";
        TaskState state = TaskState.IN_PROGRESS;
        LocalDateTime deadline = LocalDateTime.now().plusHours(17);

        Task task = new Task(id, title, content, state, deadline);

        assertThat(task.getId()).isEqualTo(id);
        assertThat(task.getTitle()).isEqualTo(title);
        assertThat(task.getContent()).isEqualTo(content);
        assertThat(task.getTaskState()).isEqualTo(state);
        task.getDeadline().ifPresent(time -> assertThat(time).isEqualTo(deadline));
    }

    @Test
    void equalsTest() {
        Task task = RandomTestData.getRandomTask();
        Task copy = new Task(task.getId(), task.getTitle(), task.getContent(), task.getTaskState(),
                task.getDeadline().get());
        assertThat(task).isEqualTo(copy);
        assertThat(copy).isEqualTo(task);
        assertThat(task).hasSameHashCodeAs(copy);
    }

    @Test
    void equalsExactlyTest() {
        Task task = RandomTestData.getRandomTask();
        assertThat(task).isEqualTo(task);
    }

    @Test
    void differentId_doesNotEqual() {
        Task task = RandomTestData.getRandomTask();
        Task copy = new Task(UUID.randomUUID(), task.getTitle(), task.getContent(), task.getTaskState(),
                task.getDeadline().get());

        assertThat(task).isNotEqualTo(copy);
        assertThat(copy).isNotEqualTo(task);
        assertThat(task).doesNotHaveSameHashCodeAs(copy);
    }

    @Test
    void differentClasses_doesNotEqual() {
        Task task = RandomTestData.getRandomTask();
        assertThat(task).isNotEqualTo(new Object());
        assertThat(new Object()).isNotEqualTo(task);
        assertThat(task).doesNotHaveSameHashCodeAs(new Object());
    }

    @ParameterizedTest
    @EnumSource(value = TaskState.class, names = {"IN_REVIEW", "IN_PROGRESS"})
    void completeTaskSuccessfully(TaskState state) {
        Task test = new Task(UUID.randomUUID(), "title", "content", state, LocalDateTime.now());
        try {
            test.completeTask();
            assertThat(test.getTaskState()).isEqualTo(TaskState.DONE);
        } catch (TaskStateException e) {
            fail(e);
        }
    }

    @ParameterizedTest
    @EnumSource(value = TaskState.class, names = {"IN_PROGRESS", "IN_REVIEW"}, mode = EnumSource.Mode.EXCLUDE)
    void completeTaskUnsuccessfully(TaskState state) {
        Task test = new Task(UUID.randomUUID(), "title", "content", state, LocalDateTime.now());
        try {
            test.completeTask();
            fail("task should not be completable");
        } catch (TaskStateException e) {
            e.printStackTrace();
        }
    }
    @Test
    void resetTaskSuccessfullyTest() {
        Task test = new Task(UUID.randomUUID(), "title", "content", TaskState.IN_PROGRESS, LocalDateTime.now());
        try {
            test.resetTask();
            assertThat(test.getTaskState()).isEqualTo(TaskState.BACKLOG);
        } catch (TaskStateException e) {
            fail(e);
        }
    }
    @ParameterizedTest
    @EnumSource(value = TaskState.class, names = {"IN_PROGRESS"}, mode = EnumSource.Mode.EXCLUDE)
    void resetTaskUnsuccessfully(TaskState state) {
        Task test = new Task(UUID.randomUUID(), "title", "content", state, LocalDateTime.now());
        try {
            test.resetTask();
            fail("task should not be completable");
        } catch (TaskStateException e) {
            e.printStackTrace();
        }
    }
    @ParameterizedTest
    @EnumSource(value = TaskState.class, names = {"IN_PROGRESS"}, mode = EnumSource.Mode.EXCLUDE)
    void startTaskSuccessfullyTest(TaskState state) {
        Task test = new Task(UUID.randomUUID(), "title", "content", state, LocalDateTime.now());
        try {
            test.startTask();
            assertThat(test.getTaskState()).isEqualTo(TaskState.IN_PROGRESS);
        } catch (TaskStateException e) {
            fail(e);
        }
    }
    @Test
    void startTaskUnsuccessfullyTest() {
        Task test = new Task(UUID.randomUUID(), "title", "content", TaskState.IN_PROGRESS, LocalDateTime.now());

        try {
            test.startTask();
            fail("task should not be completable");
        } catch (TaskStateException e) {
            e.printStackTrace();
        }
    }
    @Test
    void reviewTaskSuccessfullyTest() {
        Task test = new Task(UUID.randomUUID(), "title", "content", TaskState.IN_PROGRESS, LocalDateTime.now());
        try {
            test.reviewTask();
            assertThat(test.getTaskState()).isEqualTo(TaskState.IN_REVIEW);
        } catch (TaskStateException e) {
            fail(e);
        }
    }
    @ParameterizedTest
    @EnumSource(value = TaskState.class, names = {"IN_PROGRESS"}, mode = EnumSource.Mode.EXCLUDE)
    void reviewTaskUnsuccessfullyTest(TaskState state) {
        Task test = new Task(UUID.randomUUID(), "title", "content", state, LocalDateTime.now());
        try {
            test.reviewTask();
            fail("task should not be completable");
        } catch (TaskStateException e) {
            e.printStackTrace();
        }
    }
}