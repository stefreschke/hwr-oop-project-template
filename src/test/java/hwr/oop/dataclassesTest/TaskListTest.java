package hwr.oop.dataclassesTest;

import hwr.oop.input.TaskListException;
import hwr.oop.application.Task;
import hwr.oop.application.TaskList;

import hwr.oop.application.TaskState;
import hwr.oop.application.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListTest {
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

    @Test
    void deleteTaskTest(){
        Task task = new Task(17, "", "", TaskState.IN_PROGRESS, new ArrayList<>(),
                new User("Manfred", 27),null);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(task)));
        try {
            taskList.deleteTask(task);
            assertThat(task).isNotIn(taskList);
        } catch (TaskListException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void deleteNonexistentTaskTest() {
        Task task = new Task(17, "", "", TaskState.IN_PROGRESS, new ArrayList<>(),
                new User("Manfred", 27),null);
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            taskList.deleteTask(task);
            fail("should be unreachable");
        } catch (TaskListException e) {
            e.printStackTrace();
        }
    }
}
