package hwr.oop.handlerTests;

import hwr.oop.*;
import hwr.oop.handler.ExistenceHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExistenceHandlerTest {
    @Test
    void existenceHandlerTest() {
        ExistenceHandler existenceHandler = new ExistenceHandler();
        assertThat(existenceHandler).isNotNull();
    }
    @Test
    void handleUserCommandInvalidNumberOfArgs(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task\nDescription\n3\nBucket\n1.1.2020\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"gtd", "add", "Task", "Description", "3", "Bucket", "1.1.2020"};
        ExistenceHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer).hasToString("\u001B[1;31mInvalid number of arguments\u001B[0m\n");

    }
    @Test
    void handleUserCommandUnknownCommand(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task\nDescription\n3\nBucket\n1.1.2020\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"gtd", "addition"};
        ExistenceHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer).hasToString("\u001B[1;31mUnknown command\u001B[0m\n");

    }
    @Test
    void addCantAddExceptionTest(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task\nDescription\n3\nBucket\n1.1.2020\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.setItems(null);
        try {
            ExistenceHandler.add(toDoList, cui);
        } catch (ExistenceHandler.CouldNotAddException | ConsoleUserInterface.CouldNotReadInputException e) {
            assertThat(e).hasMessage("Could not add task");
        }
    }
    @Test
    void handleUserCommandAddTest() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task\nDescription\n3\nBucket\n1.1.2020\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"gtd", "add"};
        ExistenceHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer.toString().replace("\r", "")).hasToString("Create a new task\n" +
                "Please enter a title for your task\n" +
                "Please enter a description for your task\n" +
                "Please select a priority for your task\n" +
                "1 - LOW, 2 - MEDIUM, 3 - HIGH\n" +
                "Add a Bucket to group your tasks\n" +
                "Please enter a due date for your task\n" +
                "\u001B[1;32mTask Created Successfully!\uD83C\uDF89\u001B[0m\n");
    }
    @Test
    void handleUserCommandCouldNotAddExceptionTest(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task\nDescription\n3\nBucket\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"gtd", "add"};
        ExistenceHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer).hasToString("Create a new task\n" +
                "Please enter a title for your task\n" +
                "Please enter a description for your task\n" +
                "Please select a priority for your task\n" +
                "1 - LOW, 2 - MEDIUM, 3 - HIGH\n" +
                "Add a Bucket to group your tasks\n" +
                "Please enter a due date for your task\n" +
                "\u001B[1;33mType gtd help to get help on commands.\u001B[0m\n");
    }
    @Test
    void handleUserCommandRemoveTest() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("y\n0\n".getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.add(new ToDoItem("Task 1", "Description 1", new Bucket("Bucket 1"), Priority.LOW, LocalDate.now()));
        String[] args = {"gtd", "remove", "1"};
        ExistenceHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer.toString().replace("\r", "")).hasToString("\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                "Try again? (y/n)\n" +
                "Please enter the index of the task you want to remove.\n" +
                "Task Removed Successfully!\n");
    }
    @Test
    void testCouldNotAddException(){
        ExistenceHandler.CouldNotAddException couldNotAddException = new ExistenceHandler.CouldNotAddException("Cannot Add");
        assertEquals("Cannot Add", couldNotAddException.getMessage());
    }
}
