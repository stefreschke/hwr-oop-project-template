package hwr.oop.application.console;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

public class AddTaskConsoleTest {
    @Test
    void CanBuild(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("Add Task\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        assertThat(console).isNotNull();
    }

    @Test
    void AddTitleTest(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("Add Task\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        String output = console.AddNewTitle();

        String expectedOutput = "Please enter the title of the task:";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(output).isEqualTo("Add Task");
    }
}
