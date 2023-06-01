package hwr.oop;

import hwr.oop.util.ConsoleColors;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;



class ConsoleUITest {
    @Test
    void getEnvironmentVariablesTest() {
        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables("testSetup");
        String envString = Arrays.toString(env);
        assertThat(envString).isEqualTo("[data.json, MyList]");
    }

    @Test
    void setEnvironmentVariablesTest() {
        Program testEnvProgram = new Program();
        testEnvProgram.setEnvironmentVariables("data.json", "MyList", "setTestSetup");
        String[] env = testEnvProgram.getEnvironmentVariables("setTestSetup");
        assertThat(env).contains("data.json").contains("MyList");
    }
    @Test
    void testWelcome() {
        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables("testSetup");

        try {
            String userInput = "0\n" + "\n" + "data.json\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ToDoList toDoList = testConsole.welcome();
            // Check the program output
            String expectedOutput;
            if (env == null) {
                expectedOutput = "Welcome To Getting Things Done \uD83D\uDE80\n" +
                        "Looks Like it is your first time using this program.\n" +
                        "Lets set you up first.\n" +
                        "Please enter a name for your list\n" +
                        "> " +
                        "Please provide a filePath to an existing .json file to Load your list from.\n" +
                        "If you don't have one press enter to create specify your path.\n" +
                        "> " +
                        "Please enter your a path to a file to save your list to.\n" +
                        "> ";
            } else {
                expectedOutput = "Welcome To Getting Things Done \uD83D\uDE80\n";
            }
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList).isNotNull();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void helpTest() {
        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.help();
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "gtd [command] [arguments]\n" +
                            "Commands:\n" +
                            "  help                            -  print this help\n" +
                            "  add [Item Index]                -  add a new task\n" +
                            "  remove [Item Index]             -  remove a task\n" +
                            "  promote [Item Index]            -  promote a task to a further state\n" +
                            "  demote [Item Index]             -  demote a task to a previous state\n" +
                            "  hold [Item Index]               -  put a task on hold\n" +
                            "  done [Item Index]               -  mark a task as done\n" +
                            "  edit [Item Index]               -  edit a task\n" +
                            "  list                            -  list all tasks\n" +
                            "  sort                            -  sort your tasks\n" +
                            "  createBucket [Name]             -  create a bucket for tasks\n" +
                            "  showBuckets                     -  show buckets for tasks\n" +
                            "  renameBucket [index] [Name]     -  changes bucket name\n" +
                            "  clear                           -  clear all tasks\n" +
                            "  exit                            -  exit the program\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getTitleForAddTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String title = testConsole.getTitleForAdd();
            assertThat(title).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getTitleForDescriptionTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String desc = testConsole.getDescriptionForAdd();
            assertThat(desc).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getPriorityForAddTest(){
        try {
            String userInput = "3\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            Priority prio = testConsole.getPriorityForAdd();
            assertThat(prio).isEqualTo(Priority.HIGH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getBucketForTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String bucket = testConsole.getBucketForAdd();
            assertThat(bucket).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void listTest() {
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        toDoItems.add(new ToDoItem("Test2", "Test2", "Test", Priority.LOW));

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setItems(toDoItems);

        try {
            String userInput = "MyList\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            testConsole.list(toDoList);
            // Check the program output
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    toDoList.getItems().get(0).toString() + "\n" +
                    toDoList.getItems().get(1).toString() + "\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void listEmptyTest() {
        ToDoList toDoList = new ToDoList("MyList");
        try {
            String userInput = "MyList\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            testConsole.list(toDoList);
            // Check the program output
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    "👀Looks Empty here... Add some tasks!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void removeTest() {
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        toDoItems.add(new ToDoItem("Test2", "Test2", "Test2", Priority.LOW));

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setItems(toDoItems);

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            assertThat(toDoList.getItems()).hasSize(2);
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.remove(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Task Removed Successfully!\n";
            String actualOutput = outBuffer.toString();
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
            String actualOutput = outBuffer.toString();
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
            // funktion Main.success aufrufen
            testConsole.print(LogMode.ERROR, "Error Message");
            String expectedOutput;
            // Output den du erwartest
            expectedOutput = ConsoleColors.RED_BOLD + "Error Message" + ConsoleColors.RESET + "\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void handleBadIndexTest() {
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("y\n1\n".getBytes(StandardCharsets.UTF_8)));
            int index = testConsole.handleBadIndex("Test Message.");
            // Check the program output
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "There is nothing at that index... \uD83E\uDD78" + ConsoleColors.RESET + "\n" +
                    "Try again? (y/n)\n" +
                    "Test Message.\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(index).isEqualTo(1);
            System.setIn(System.in);
        }
        catch (Exception e) {
            System.setIn(System.in);
            throw new RuntimeException(e);
        }
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("n\n".getBytes(StandardCharsets.UTF_8)));
            int index = testConsole.handleBadIndex("Test Message.");
            // Check the program output
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "There is nothing at that index... \uD83E\uDD78" + ConsoleColors.RESET + "\n" +
                    "Try again? (y/n)\n" +
                    "Okay, I'll leave you alone then. 👋\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(index).isEqualTo(-1);
            System.setIn(System.in);
        }
        catch (Exception e) {
            System.setIn(System.in);
            throw new RuntimeException(e);
        }
    }
    @Test
    void sortHelpTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.sortHelp();
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "gtd sort [option]\n" +
                            "Options:\n" +
                            "  priority - sort by priority\n" +
                            "  createdAt- sort by creation date\n" +
                            "  dueDate  - sort by due date\n" +
                            "  bucket [bucket]- sort by bucket\n" +
                            "  title    - sort by title\n" +
                            "  done     - sort by done\n" +
                            "  help     - print this help\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void handleSortTest() {
        String[] commandArray = {"gtd", "sort", "prio", "asc"};
        ToDoList toDoList = new ToDoList("MyList");
        toDoList.add(new ToDoItem("Apple", "Computers", "Fruit", Priority.MEDIUM));
        toDoList.getItems().get(0).setCreatedAt(LocalDateTime.of(2020, 1, 1, 0, 0));
        toDoList.add(new ToDoItem("Cucumber", "Water", "Vegetable", Priority.LOW));
        toDoList.getItems().get(1).setCreatedAt(LocalDateTime.of(2020, 1, 2, 0, 0));
        toDoList.add(new ToDoItem("Banana", "Minions", "Fruit", Priority.HIGH));

        // Priority Test
        toDoList.sortByPriority("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Cucumber");
        toDoList.sortByPriority("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");
        toDoList.sortByCreatedAt("asc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Apple");
        toDoList.sortByCreatedAt("desc");
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");
        toDoList.bubbleUpBucket(commandArray[3]);
        assertThat(toDoList.getItems().get(0).getTitle()).isEqualTo("Banana");
    }
}
