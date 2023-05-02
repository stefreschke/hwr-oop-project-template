package hwr.oop.application.console;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

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
        String output = console.addNewTitle();

        String expectedOutput = "Please enter the title of the task:";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(output).isEqualTo("Add Task");
    }

    @Test
    void addDescriptionTest(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("Add Description\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        String output = console.addNewDescription();

        String expectedOutput = "Please enter the description of the task:";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(output).isEqualTo("Add Description");
    }

    @Test
    void addDeadlineTest(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("2021-01-01\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        LocalDateTime output = console.addNewDeadline();
        String outputString = output.toString();

        String expectedOutput = "Please enter the deadline of the task(optional)(format yyyy-MM-dd):";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(outputString).contains("2021-01-01");
    }

    @Test
    void addPriorityHigh(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("HIGH\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        String output = console.addNewPriority().toString();

        String expectedOutput = "Please enter the priority of the task(optional)(HIGH, MEDIUM, LOW):";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(output).isEqualTo("HIGH");
    }

    @Test
    void addPriorityMedium(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("MEDIUM\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        String output = console.addNewPriority().toString();

        String expectedOutput = "Please enter the priority of the task(optional)(HIGH, MEDIUM, LOW):";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(output).isEqualTo("MEDIUM");
    }

    @Test
    void addPriorityLow(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("LOW\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        String output = console.addNewPriority().toString();

        String expectedOutput = "Please enter the priority of the task(optional)(HIGH, MEDIUM, LOW):";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(output).isEqualTo("LOW");
    }

    @Test
    void addPriorityUndetermined(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        String output = console.addNewPriority().toString();

        String expectedOutput = "Please enter the priority of the task(optional)(HIGH, MEDIUM, LOW):";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(output).isEqualTo("UNDETERMINED");
    }

    @Test
    void addTimeStartTest(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("2021-01-01\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        LocalDateTime output = console.addTimeStart();
        String outputString = output.toString();

        String expectedOutput = "Please enter the start time of the task(optional)(format yyyy-MM-dd):";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(outputString).contains("2021-01-01");
    }

    @Test
    void addTimeEndTest(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream("2021-01-01\n".getBytes());
        System.setIn(inputStream);

        AddTaskConsole console = new AddTaskConsole(outputStream, inputStream);
        LocalDateTime output = console.addTimeEnd();
        String outputString = output.toString();

        String expectedOutput = "Please enter the end time of the task(optional)(format yyyy-MM-dd):";
        String actualOutput = outputStream.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
        assertThat(outputString).contains("2021-01-01");
    }

}
