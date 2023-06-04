package hwr.oop.applicationTest;

import hwr.oop.application.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class TaskTest {

    @Test
    void getDeadline() {
        Task example = new Task(UUID.randomUUID(), "Title","Content", TaskState.IN_PROGRESS, LocalDateTime.now());
        Optional<LocalDateTime> result = example.getDeadline();
        result.ifPresent(localDate -> assertThat(localDate).isBetween(LocalDateTime.now().minusHours(1),LocalDateTime.now()));
    }

    @Test
    void getTitle() {
        Task example = new Task(UUID.randomUUID(), "Title","Content", TaskState.IN_PROGRESS, LocalDateTime.now());
        String result = example.getTitle();
        assertThat(result).isEqualTo("Title");
    }

    @Test
    void getContent() {
        Task example = new Task(UUID.randomUUID(), "Title","Content", TaskState.IN_PROGRESS, LocalDateTime.now());
        String result = example.getContent();
        assertThat(result).isEqualTo("Content");
    }

    @Test
    void getTaskState() {
        Task example = new Task(UUID.randomUUID(), "Title","Content", TaskState.IN_PROGRESS, LocalDateTime.now());
        TaskState result = example.getTaskState();
        assertThat(result).isEqualTo(TaskState.IN_PROGRESS);
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
            test.reviewTask();
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