package hwr.oop.handlerTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.Priority;
import hwr.oop.ToDoItem;
import hwr.oop.ToDoList;
import hwr.oop.handler.StateHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StateHandlerTest {
    @Test
    void doneTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        assertThat(toDoList.getItems()[0].isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "done", "0"});
        assertThat(toDoList.getItems()[0].isDone()).isTrue();
    }
    @Test
    void promoteTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        assertThat(toDoList.getItems()[0].isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "p", "0"});
        assertThat(toDoList.getItems()[0].getState()).isEqualTo("IN_PROGRESS");
    }
    @Test
    void demoteTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        assertThat(toDoList.getItems()[0].isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "p", "0"});
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "p", "0"});
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "d", "0"});
        assertThat(toDoList.getItems()[0].getState()).isEqualTo("IN_PROGRESS");
    }
    @Test
    void holdTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        assertThat(toDoList.getItems()[0].isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "p", "0"});
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "hold", "0"});
        assertThat(toDoList.getItems()[0].getState()).isEqualTo("ON_HOLD");
    }
    @Test
    void holdToDoTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        assertThat(toDoList.getItems()[0].isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.handleUserCommand(toDoList, testConsole, new String[]{"gtd", "hold", "0"});
        assertThat(toDoList.getItems()[0].getState()).isEqualTo("TODO");
    }
}
