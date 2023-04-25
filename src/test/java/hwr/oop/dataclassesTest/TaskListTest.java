package hwr.oop.dataclassesTest;

import hwr.oop.dataclasses.Task;
import hwr.oop.dataclasses.TaskList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskListTest {
    @Test
    void addTaskTest(){
        TaskList taskList = new TaskList(new ArrayList<>());
        Task taskTmp = new Task(23,"","",null,null,null,null);
        taskList.addTask(taskTmp);
        Assertions.assertThat(taskList.getTasks().get(0)).isEqualTo(taskTmp);
    }

    @Test
    void getTasksTest(){
        List<Task> tasksTmp = new ArrayList<>();
        tasksTmp.add(new Task(23,"","",null,null,null,null));
        TaskList taskList = new TaskList(tasksTmp);
        Assertions.assertThat(taskList.getTasks()).isEqualTo(tasksTmp);

    }
}
