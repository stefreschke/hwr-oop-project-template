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

class ExistenceHandlerTest {
    @Test
    void handleUserCommandAddTest() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task\nDescription\n3\nBucket\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"gtd", "add"};
        ExistenceHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer.toString()).isEqualTo("Task added successfully!");
    }

    @Test
    void handleUserCommandRemoveTest() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("y\n0\n".getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.add(new ToDoItem("Task 1", "Description 1", new Bucket("Bucket 1"), Priority.LOW, LocalDate.now()));
        String[] args = {"gtd", "remove", "1"};
        ExistenceHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer).hasToString("\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                "Try again? (y/n)\n" +
                "Please enter the index of the task you want to remove.\n" +
                "Task Removed Successfully!\n");
    }
}
