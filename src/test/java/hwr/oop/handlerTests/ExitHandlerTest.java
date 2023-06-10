package hwr.oop.handlerTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;
import hwr.oop.handler.ExitHandler;
import hwr.oop.persistence.PersistenceFileNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class ExitHandlerTest {
    @Test
    void handleUserCommandTest() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"gtd", "ex"};
        try {
            ExitHandler.handleUserCommand(toDoList, cui, args);
        } catch (PersistenceFileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String expected = "\u001B[1;31mCould not exit... If that is what you wanted to do, try 'gtd exit'\u001B[0m\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
}
