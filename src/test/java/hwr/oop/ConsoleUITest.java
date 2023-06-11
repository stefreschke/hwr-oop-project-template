package hwr.oop;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ConsoleUserInterface.LogMode;
import hwr.oop.handler.BucketHandler;
import hwr.oop.handler.ExistenceHandler;
import hwr.oop.handler.SortHandler;
import hwr.oop.util.ConsoleColors;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleUITest {

    @Test
    void getInputStreamTest() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        assertThat(testConsole.getInputStream()).isEqualTo(inputStream);
    }

    @Test
    void getOutputStreamTest() {
        OutputStream outBuffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outBuffer);
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(out, inputStream);
        assertThat(testConsole.getOutputStream()).isEqualTo(out);
    }

    @Test
    void printTest() {
        LogMode mode = LogMode.SUCCESS;
        OutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        String expectedOutput = mode.getColor() + "Test" + ConsoleColors.RESET + "\n";
        testConsole.print(mode, "Test");
        String actualOutput = outBuffer.toString().replace("\r","");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    @Test
    void handleSortTest() {
        ToDoList toDoList = new ToDoList("MyList");
        toDoList.add(new ToDoItem("Apple", "Computers", new Bucket("Fruit"), Priority.MEDIUM, LocalDate.now(), EstimatedTime.SHORT));
        toDoList.getItems().get(0).setCreatedAt(LocalDate.of(2020, 1, 1));
        toDoList.add(new ToDoItem("Cucumber", "Water", new Bucket("Vegetable"), Priority.LOW, LocalDate.now().plusDays(1), EstimatedTime.MEDIUM));
        toDoList.getItems().get(1).setCreatedAt(LocalDate.of(2020, 1, 2));
        toDoList.add(new ToDoItem("Banana", "Minions", new Bucket("Weapon"), Priority.HIGH, LocalDate.now().plusDays(2), EstimatedTime.LONG));
      
        toDoList.sortByPriority("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Cucumber");
        toDoList.sortByPriority("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");

        toDoList.sortByCreatedAt("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Apple");
        toDoList.sortByCreatedAt("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");

        toDoList.bubbleUpBucket("Weapon");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");

        toDoList.sortByTitle("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Apple");
        toDoList.sortByTitle("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Cucumber");

        toDoList.getItems().get(1).setDone();
        toDoList.sortByDone("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Cucumber");
        toDoList.sortByDone("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");

        toDoList.sortByDueDate("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Apple");
        toDoList.sortByDueDate("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");
    }

    @Test
    void removeTest() {
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("Test", "Test", new Bucket("Test"), Priority.LOW, LocalDate.now(), EstimatedTime.LONG));
        toDoItems.add(new ToDoItem("Test2", "Test2", new Bucket("Test2"), Priority.LOW, LocalDate.now(), EstimatedTime.LONG));

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setItems(toDoItems);

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            assertThat(toDoList.getItems()).hasSize(2);
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            new ExistenceHandler().remove(testConsole, toDoList, 0);
            String expectedOutput;
            expectedOutput = "Task Removed Successfully!\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getItems()).hasSize(1);
            assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Test2");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void successTest() {
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.print(LogMode.SUCCESS, "great success");
            String expectedOutput;
            expectedOutput = ConsoleColors.GREEN_BOLD + "great success" + ConsoleColors.RESET +"\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void errorTest() {
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.print(LogMode.ERROR, "Error Message");
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "Error Message" + ConsoleColors.RESET + "\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void sortHelpTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            SortHandler.sortHelp(testConsole);
            String expectedOutput;
            expectedOutput =
                    "gtd sort [option]\n" +
                            "Options:\n" +
                            "  priority - sort by priority\n" +
                            "  createdAt- sort by creation date\n" +
                            "  dueDate  - sort by due date\n" +
                            "  estimatedTime - sort by estimated time\n" +
                            "  bucket [bucket] - sort by bucket\n" +
                            "  title    - sort by title\n" +
                            "  done     - sort by done\n" +
                            "  help     - print this help\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void showBucketsTestEmpty() {
        OutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        ToDoList toDoList = new ToDoList("Test");
        new BucketHandler().showBuckets(toDoList, testConsole);
        String expectedOutput = "\uD83D\uDC40Looks Empty here... Add some buckets!";
        String actualOutput = outBuffer.toString().replace("\r","").replace("\n","");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    void showBucketsTestElement() {
        OutputStream outBuffer = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), inputStream);
        ToDoList toDoList = new ToDoList("Test");
        toDoList.addBucket(new Bucket("Test"));
        new BucketHandler().showBuckets(toDoList, testConsole);
        String expectedOutput = "🪣Test\n";
        String actualOutput = outBuffer.toString().replace("\r","");
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    @Test
    void testCouldNotReadInputException(){
        ConsoleUserInterface.CouldNotReadInputException couldNotReadInputException = new ConsoleUserInterface.CouldNotReadInputException();
        assertEquals("Could not read your input... skipping", couldNotReadInputException.getMessage());
    }
}
