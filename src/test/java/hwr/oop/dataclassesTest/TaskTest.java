package hwr.oop.dataclassesTest;

import hwr.oop.dataclasses.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class TaskTest {

    @Test
    void getDeadline() {
        Task example = new Task(1,"Title","Content", TaskState.IN_PROGRESS,
                null, new User("Name",12), LocalDateTime.now());
        Optional<LocalDateTime> result = example.getDeadline();
       result.ifPresent(localDate -> assertThat(localDate).isBetween(LocalDateTime.now().minusHours(1),LocalDateTime.now()));
    }

   @Test
    void getId() {
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null, new User("Name",12), LocalDateTime.now());
        Integer result = example.getId();
        assertThat(result).isEqualTo(69);
    }

    @Test
    void getTitle() {
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null, new User("Name",12), LocalDateTime.now());
        String result = example.getTitle();
        assertThat(result).isEqualTo("Title");
    }

    @Test
    void getContent() {
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null, new User("Name",12), LocalDateTime.now());
        String result = example.getContent();
        assertThat(result).isEqualTo("Content");
    }

    @Test
    void getTaskState() {
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null, new User("Name",12), LocalDateTime.now());
        TaskState result = example.getTaskState();
        assertThat(result).isEqualTo(TaskState.IN_PROGRESS);
    }

    @Test
    void getTaskTagList() {
        List<TaskTag> list = new ArrayList<>();
        list.add(new TaskTag("Kitchen",2));
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                list, new User("Name",12), LocalDateTime.now());
        List<TaskTag> result = example.getTaskTagList();
        assertThat(result).isEqualTo(list);
    }

    @Test
    void getCreator() {
        User user = new User("Name",12);
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null, user, LocalDateTime.now());
        User result = example.getCreator();
       assertThat(result).isEqualTo(user);
    }

    @ParameterizedTest
    @EnumSource(value = TaskState.class, names = {"IN_REVIEW", "IN_PROGRESS"})
    void completeTaskSuccessfully(TaskState state) {
        User user = new User("Manfred", 17);
        Task test = new Task(1, "title", "content", state, null, user, LocalDateTime.now());
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
        User user = new User("Manfred", 17);
        Task test = new Task(1, "title", "content", state, null, user, LocalDateTime.now());
        try {
            test.completeTask();
            fail("task should not be completable");
        } catch (TaskStateException e) {
            e.printStackTrace();
        }
    }
    @Test
    void resetTaskSuccessfullyTest() {
        User user = new User("Manfred", 17);
        Task test = new Task(1, "title", "content", TaskState.IN_PROGRESS, null, user, LocalDateTime.now());
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
        User user = new User("Manfred", 17);
        Task test = new Task(1, "title", "content", state, null, user, LocalDateTime.now());
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
        User user = new User("Manfred", 17);
        Task test = new Task(1, "title", "content", state, null, user, LocalDateTime.now());
        try {
            test.startTask();
            assertThat(test.getTaskState()).isEqualTo(TaskState.IN_PROGRESS);
        } catch (TaskStateException e) {
            fail(e);
        }
    }
    @Test
    void startTaskUnsuccessfullyTest() {
        User user = new User("Manfred", 17);
        Task test = new Task(1, "title", "content", TaskState.IN_PROGRESS, null, user, LocalDateTime.now());

        try {
            test.startTask();
            fail("task should not be completable");
        } catch (TaskStateException e) {
            e.printStackTrace();
        }
    }
    @Test
    void reviewTaskSuccessfullyTest() {
        User user = new User("Manfred", 17);
        Task test = new Task(1, "title", "content", TaskState.IN_PROGRESS, null, user, LocalDateTime.now());
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
        User user = new User("Manfred", 17);
        Task test = new Task(1, "title", "content", state, null, user, LocalDateTime.now());
        try {
            test.reviewTask();
            fail("task should not be completable");
        } catch (TaskStateException e) {
            e.printStackTrace();
        }
    }
}