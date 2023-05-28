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

public class StateHandlerTest {
    @Test
    void doneTest() {
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        assertThat(toDoList.getItems()[0].isDone()).isFalse();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(System.out, new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        StateHandler.done(toDoList, testConsole, new String[]{"gtd", "done", "0"});
        assertThat(toDoList.getItems()[0].isDone()).isTrue();
    }
}
