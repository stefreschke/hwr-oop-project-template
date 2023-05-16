package hwr.oop.todo;

import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskController;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.todolist.ToDoListException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    @Test
    void canGetTaskController(){
        TaskController testController = TaskController.get();
        Assertions.assertNotNull(testController);
    }

    @Test
    void canGetToDoList(){
        TaskController testController = TaskController.get();
        Assertions.assertNotNull(testController.getToDoList());
    }

    @Test
    void canStoreTask() {
        TaskController testController = new TaskController();
        Task task = TaskFactory.createTask("Title");

        assertDoesNotThrow(() -> testController.addTask(task));
    }


    @Test
    void cannotRetrieveTaskWithNonExistingID() {
        TaskController testController = new TaskController();
        UUID id = UUID.randomUUID();

        assertThrows(ToDoListException.class, () -> testController.getTask(id));
    }


    @Test
    void canStoreMultipleTasks() {
        TaskController testController = new TaskController();

        Task firstTask = TaskFactory.createTask("Title");
        Task secondTask = TaskFactory.createTask("Title2");
        
        testController.addTask(firstTask);
        testController.addTask(secondTask);
    }


    @Test
    void canStoreMultipleTasksWithSameTitle() {
        TaskController testController = new TaskController();

        Task firstTask = TaskFactory.createTask("Title");
        Task secondTask = TaskFactory.createTask("Title");
        
        testController.addTask(firstTask);
        testController.addTask(secondTask);
    }
}