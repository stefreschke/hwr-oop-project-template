package hwr.oop;

import hwr.oop.util.ConsoleColors;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void getEnvironmentVariablesTest() {
        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        assertThat(env).isNotNull();
    }

    @Test
    void setEnvironmentVariablesTest() {
        Program testEnvProgram = new Program();
        testEnvProgram.setEnvironmentVariables("data.json", "MyList");
        String[] env = testEnvProgram.getEnvironmentVariables();
        assertThat(env).contains("data.json");
        assertThat(env).contains("MyList");
    }
    @Test
    void testWelcome() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        try {
            String userInput = "0\n" + "\n" + "data.json\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ToDoList toDoList = cui.welcome();
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
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void listTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        ToDoItem[] toDoItems = new ToDoItem[2];
        toDoItems[0] = new ToDoItem("Test", "Test", "Test", Priority.LOW);
        toDoItems[1] = new ToDoItem("Test2", "Test2", "Test2", Priority.LOW);

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setItems(toDoItems);

        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            cui.list(toDoList);
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    toDoList.getItems()[0].toString() + "\n" +
                    toDoList.getItems()[1].toString() + "\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);

        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void listEmptyTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        ToDoList toDoList = new ToDoList("MyList");
        try {
            String userInput = "MyList\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            cui.list(toDoList);
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    "👀Looks Empty here... Add some tasks!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void removeFileNotSpecifiedTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        ToDoItem[] toDoItems = new ToDoItem[2];
        toDoItems[0] = new ToDoItem("Test", "Test", "Test", Priority.LOW);
        toDoItems[1] = new ToDoItem("Test2", "Test2", "Test2", Priority.LOW);

        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setItems(toDoItems);

        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(outBuffer));
            assertThat(toDoList.getItems()).hasSize(2);
            cui.remove(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "Task Removed Successfully!\n" +
                    "Could not save your progress... please specify a file or try again.\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getItems()).hasSize(1);
            assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("Test2");
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void removeFileSpecifiedTest() {
        ToDoItem[] toDoItems = new ToDoItem[2];
        toDoItems[0] = new ToDoItem("Test", "Test", "Test", Priority.LOW);
        toDoItems[1] = new ToDoItem("Test2", "Test2", "Test2", Priority.LOW);
        ToDoList toDoList = new ToDoList("MyList", "removeTestFile");
        toDoList.setItems(toDoItems);
        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            assertThat(toDoList.getItems()).hasSize(2);
            cui.remove(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Task Removed Successfully!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getItems()).hasSize(1);
            assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("Test2");
        } finally {
            // Restore standard input and output streams
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }
    @Test
    void successTest() {
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.print(LogMode.SUCCESS, "Great Success");
            String expectedOutput;
            expectedOutput = ConsoleColors.GREEN_BOLD + "Great Success" + ConsoleColors.RESET +"\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void errorTest() {
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.print(LogMode.ERROR, "Error Message");
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "Error Message" + ConsoleColors.RESET + "\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(System.out);
        }
    }
    @Test
    void handleBadIndexTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            System.setIn(new ByteArrayInputStream("y\n1\n".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            int index = cui.handleBadIndex("Test Message.");
            // Check the program output
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "There is nothing at that index... \uD83E\uDD78" + ConsoleColors.RESET + "\n" +
                    "Try again? (y/n)\n" +
                    "Test Message.\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(index).isEqualTo(1);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
        try {
            System.setIn(new ByteArrayInputStream("n\n".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            int index = cui.handleBadIndex("Test Message.");
            // Check the program output
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "There is nothing at that index... \uD83E\uDD78" + ConsoleColors.RESET + "\n" +
                    "Try again? (y/n)\n" +
                    "Okay, I'll leave you alone then. 👋\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(index).isEqualTo(-1);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void sortHelpTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            Main.sortHelp();
            String expectedOutput;
            expectedOutput =
                    "gtd sort [option]\n" +
                    "Options:\n" +
                    "  priority        - sort by priority\n" +
                    "  createdAt       - sort by creation date\n" +
                    "  dueDate         - sort by due date\n" +
                    "  bucket [Bucket] - sort by bucket\n" +
                    "  title           - sort by title\n" +
                    "  done            - sort by done\n" +
                    "  help            - print this help\n";
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
        toDoList.getItems()[0].setCreatedAt(LocalDateTime.of(2020, 1, 1, 0, 0));
        toDoList.add(new ToDoItem("Cucumber", "Water", "Vegetable", Priority.LOW));
        toDoList.getItems()[1].setCreatedAt(LocalDateTime.of(2020, 1, 2, 0, 0));
        toDoList.add(new ToDoItem("Banana", "Minions", "Fruit", Priority.HIGH));

        // Priority Test
        toDoList.sortByPriority("asc");
        assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("Cucumber");
        toDoList.sortByPriority("desc");
        assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("Banana");
        toDoList.sortByCreatedAt("asc");
        assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("Apple");
        toDoList.sortByCreatedAt("desc");
        assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("Banana");
        toDoList.bubbleUpBucket(commandArray[3]);
        assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("Banana");
    }
    @Test
    void exitTest() {
        PrintStream sysOutBackup = System.out;
        ToDoList toDoList = new ToDoList("MyList");
        toDoList.setFileName("listTest.json");
        toDoList.add(new ToDoItem("Apple", "Computers", "Fruit", Priority.MEDIUM));
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            Main.exit(cui, toDoList);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Exiting...\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            ToDoList testList = new Program().loadToDoList("listTest.json");
            assertThat(testList.getItems()[0].getTitle()).isEqualTo("Apple");
        } catch (ConsoleUserInterface.CouldNotSaveChangesException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(sysOutBackup);
        }
    }
}
