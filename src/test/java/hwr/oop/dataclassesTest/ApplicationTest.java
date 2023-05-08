package hwr.oop.dataclassesTest;

import hwr.oop.clicode.Application;
import hwr.oop.dataclasses.Task;
import hwr.oop.dataclasses.TaskState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class ApplicationTest {
    @Test
    void addTaskTest(){
        Task taskTmp = new Task(0,"Test","Lol", TaskState.IN_PROGRESS,null,null, LocalDateTime.now());
        Application app = new Application();
        app.addTask(taskTmp.getTitle(),taskTmp.getContent(),taskTmp.getTaskState(),taskTmp.getCreator(),taskTmp.getDeadline().get());
        Task testObject = app.getTaskByID(0);
        Assertions.assertThat(testObject.getTitle()).isEqualTo(taskTmp.getTitle());
        Assertions.assertThat(testObject.getContent()).isEqualTo(taskTmp.getContent());
        Assertions.assertThat(testObject.getTaskState()).isEqualTo(taskTmp.getTaskState());
        Assertions.assertThat(testObject.getCreator()).isEqualTo(taskTmp.getCreator());
        Assertions.assertThat(testObject.getDeadline()).isEqualTo(taskTmp.getDeadline());
    }
    @Test
    void getTaskByIdTest(){
        Application app = new Application();
        Assertions.assertThat(app.getTaskByID(100)).isNull();
    }
}
