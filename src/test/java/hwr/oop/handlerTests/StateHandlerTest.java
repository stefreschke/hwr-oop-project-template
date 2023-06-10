package hwr.oop.handlerTests;

import hwr.oop.*;
import hwr.oop.handler.StateHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StateHandlerTest {
    @Test
    void stateHandlerTest() {
        StateHandler stateHandler = new StateHandler();
        assertThat(stateHandler).isNotNull();
    }
    @Test
    void doneTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "done", "0"});
        assertThat(toDoList.getItems().get(0).isDone()).isTrue();
    }
    @Test
    void doneTestBadIndex() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("n\n".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "done", "1"});
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        assertThat(outBuffer).hasToString("\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                "Try again? (y/n)\n" +
                "Okay, I'll leave you alone then. \uD83D\uDC4B\n");
    }
    @Test
    void handleUserCommandTestInsufficientArgs(){
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "done"});
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        assertThat(outBuffer).hasToString("\u001B[1;33mType gtd help to get help on commands.\u001B[0m\n");
    }
    @Test
    void handleUserCommandTestUnknownCommand(){
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "doneston"});
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        assertThat(outBuffer).hasToString("\u001B[1;31mUnknown command\u001B[0m\n");
    }
    @Test
    void handleUserCommandTestTooManyArgs(){
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "done", "0", "1"});
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        assertThat(outBuffer).hasToString("\u001B[1;31mInvalid number of arguments\u001B[0m\n");
    }
    @Test
    void promoteTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "p", "0"});
        assertThat(toDoList.getItems().get(0).getState()).isEqualTo("IN_PROGRESS");
    }
    @Test
    void promoteTestBadIndex() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("n\n".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "promote", "1"});
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        assertThat(outBuffer).hasToString("\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                "Try again? (y/n)\n" +
                "Okay, I'll leave you alone then. \uD83D\uDC4B\n");
    }
    @Test
    void demoteTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "p", "0"});
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "p", "0"});
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "d", "0"});
        assertThat(toDoList.getItems().get(0).getState()).isEqualTo("IN_PROGRESS");
    }
    @Test
    void demoteTestBadIndex() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("n\n".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "demote", "1"});
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        assertThat(outBuffer).hasToString("\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                "Try again? (y/n)\n" +
                "Okay, I'll leave you alone then. \uD83D\uDC4B\n");
    }
    @Test
    void holdTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "p", "0"});
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "hold", "0"});
        assertThat(toDoList.getItems().get(0).getState()).isEqualTo("ON_HOLD");
    }
    @Test
    void holdTestBadIndex() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("n\n".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "hold", "1"});
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        assertThat(outBuffer).hasToString("\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                "Try again? (y/n)\n" +
                "Okay, I'll leave you alone then. \uD83D\uDC4B\n");
    }
    @Test
    void holdToDoTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test",  new Bucket("Test"), Priority.LOW, LocalDate.now()));
        assertThat(toDoList.getItems().get(0).isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "hold", "0"});
        assertThat(toDoList.getItems().get(0).getState()).isEqualTo("TODO");
    }
}
