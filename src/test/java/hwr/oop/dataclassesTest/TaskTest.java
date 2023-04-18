package hwr.oop.dataclassesTest;

import hwr.oop.dataclasses.Task;
import hwr.oop.dataclasses.TaskState;
import hwr.oop.dataclasses.TaskTag;
import hwr.oop.dataclasses.User;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class TaskTest {

    @Test
    void getDeadline() {
        Task example = new Task(1,"Title","Content", TaskState.IN_PROGRESS,
                null,new User("Name",12), LocalDate.now(),LocalDate.now());
        Optional<LocalDate> result = example.getDeadline();
       result.ifPresent(localDate -> Assertions.assertThat(localDate).isBetween(LocalDate.now().minusDays(1),LocalDate.now()));
    }

   @Test
    void getId() {
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null,new User("Name",12), LocalDate.now(),LocalDate.now());
        Integer result = example.getId();
        Assertions.assertThat(result).isEqualTo(69);
    }

    @Test
    void getTitle() {
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null,new User("Name",12), LocalDate.now(),LocalDate.now());
        String result = example.getTitle();
        Assertions.assertThat(result).isEqualTo("Title");
    }

    @Test
    void getContent() {
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null,new User("Name",12), LocalDate.now(),LocalDate.now());
        String result = example.getContent();
        Assertions.assertThat(result).isEqualTo("Content");
    }

    @Test
    void getTaskState() {
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null,new User("Name",12), LocalDate.now(),LocalDate.now());
        TaskState result = example.getTaskState();
        Assertions.assertThat(result).isEqualTo(TaskState.IN_PROGRESS);
    }

    @Test
    void getTaskTagList() {
        List<TaskTag> list = new ArrayList<>();
        list.add(new TaskTag("Kitchen",2));
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                list,new User("Name",12), LocalDate.now(),LocalDate.now());
        List<TaskTag> result = example.getTaskTagList();
        Assertions.assertThat(result).isEqualTo(list);
    }

    @Test
    void getCreator() {
        User user = new User("Name",12);
        Task example = new Task(69,"Title","Content", TaskState.IN_PROGRESS,
                null,user, LocalDate.now(),LocalDate.now());
        User result = example.getCreator();
       Assertions.assertThat(result).isEqualTo(user);
    }
}