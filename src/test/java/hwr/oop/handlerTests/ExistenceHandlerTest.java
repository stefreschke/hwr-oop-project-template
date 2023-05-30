package hwr.oop.handlerTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.Priority;
import hwr.oop.ToDoItem;
import hwr.oop.ToDoList;
import hwr.oop.handler.ExistenceHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class ExistenceHandlerTest {
    @Test
    void handleUserCommandAddTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"", "add"};

        // Act
        ExistenceHandler.handleUserCommand(toDoList, cui, args);

        // Assert
        assertThat(outBuffer.toString()).contains("Create a new task");
        // Add more assertions to verify the behavior of the add command
    }

    @Test
    void handleUserCommandRemoveTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("y\n0\n".getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.add(new ToDoItem("Task 1", "Description 1", "Bucket 1", Priority.LOW));
        String[] args = {"gtd", "remove", "1"};
        // Act
        ExistenceHandler.handleUserCommand(toDoList, cui, args);
        // Assert
        assertThat(outBuffer.toString()).isEqualTo("\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                "Try again? (y/n)\n" +
                "Please enter the index of the task you want to remove.\n" +
                "Task Removed Successfully!\n");
        // Add more assertions to verify the behavior of the remove command
    }
}

