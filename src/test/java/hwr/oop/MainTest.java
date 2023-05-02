package hwr.oop;

import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyAppTest {
    @Test
    void getEnvironmentVariablesTest() {
        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        if (env == null) {
            System.out.println("No environment variables found");
        } else {
            for (String envVar : env) {
                System.out.format("%s=%s%n",
                        envVar,
                        System.getenv(envVar));
            }
        }
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
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();

        try {
            String userInput = "0\n" + "" +"\n" + "data.json\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            List toDoList = Main.welcome();
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void helpTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            Main.help();
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "gtd [command] [arguments]\n" +
                    "Commands:\n" +
                    "  help                -  print this help\n" +
                    "  add [Item Index]    -  add a new task\n" +
                    "  remove [Item Index] -  remove a task\n" +
                    "  done [Item Index]   -  mark a task as done\n" +
                    "  edit [Item Index]   -  edit a task\n" +
                    "  sort                -  sort your tasks\n" +
                    "  clear               -  clear all tasks\n" +
                    "  exit                -  exit the program\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void testAdd() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            String userInput = "Title\nDescription\n12.12.12\n3\nTag\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            List toDoList = new List("MyList");
            Main.add(toDoList);
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "Create a new task\n" +
                    "Please enter a title for your task\n" +
                    "Please enter a description for your task\n" +
                    "Please enter a due date for your task\n" +
                    "Please enter a priority for your task\n" +
                    "1 - LOW, 2 - MEDIUM, 3 - HIGH\n" +
                    "Add a Tag to group your tasks\n" +
                    "\u001B[1;32mTask Created Successfully!\u001B[0m\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getListToDos()[0].getTitle()).isEqualTo("Title");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void testEdit() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();

        try {
            String userInput = "MyList\nDescription\n3\nTag\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            List toDoList = new List("MyList");
            toDoList.add(new ToDoItem(0, "Test", "Test", "Test", false, Priority.LOW, new Project("Test")));
            Main.edit(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Editing task at index 0:\n" +
                    "❌ Test\n" +
                    "Test\n" +
                    "Created " + toDoList.getListToDos()[0].getCreatedAt() + "\n" +
                    "<Test> LOW\n\n" +
                    "Enter new Title or press enter to skip\n" +
                    "Enter new Description or press enter to skip\n" +
                    "Enter new Priority or press enter to skip\n" +
                    "1 - LOW, 2 - MEDIUM, 3 - HIGH\n" +
                    "Enter new Tag or press enter to skip\n" +
                    "Task Edited Successfully!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getListToDos()[0].getTitle()).isEqualTo("MyList");
            assertThat(toDoList.getListToDos()[0].getDescription()).isEqualTo("Description" + "\n" + "Created " + toDoList.getListToDos()[0].getCreatedAt());
            assertThat(toDoList.getListToDos()[0].getPriority()).isEqualTo(Priority.HIGH);
            assertThat(toDoList.getListToDos()[0].getTag()).isEqualTo("Tag");

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
        toDoItems[0] = new ToDoItem(0, "Test", "Test", "Test", false, Priority.LOW, new Project("Test"));
        toDoItems[1] = new ToDoItem(1, "Test2", "Test2", "Test2", false, Priority.LOW, new Project("Test2"));

        List toDoList = new List("MyList");
        toDoList.setListToDos(toDoItems);

        try {
            String userInput = "MyList\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            Main.list(toDoList);
            // Check the program output
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    toDoList.getListToDos()[0].toString() + "\n" +
                    toDoList.getListToDos()[1].toString() + "\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);

        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void removeTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        ToDoItem[] toDoItems = new ToDoItem[2];
        toDoItems[0] = new ToDoItem(0, "Test", "Test", "Test", false, Priority.LOW, new Project("Test"));
        toDoItems[1] = new ToDoItem(1, "Test2", "Test2", "Test2", false, Priority.LOW, new Project("Test2"));

        List toDoList = new List("MyList");
        toDoList.setListToDos(toDoItems);

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            assertThat(toDoList.getListToDos().length).isEqualTo(2);
            Main.remove(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Task Removed Successfully!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getListToDos().length).isEqualTo(1);
            assertThat(toDoList.getListToDos()[0].getTitle()).isEqualTo("Test2");
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void successTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        List testList = new List("Test");
        testList.setListToDos(new ToDoItem[3]);

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            // funktion Main.success aufrufen
            Main.success("greatsuccess");
            String expectedOutput;
            // Output den du erwartest
            expectedOutput = ConsoleColors.GREEN_BOLD + "greatsuccess" + ConsoleColors.RESET +"\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void errorTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        List testList = new List("Test");
        testList.setListToDos(new ToDoItem[3]);

        try {
            System.setIn(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            // funktion Main.success aufrufen
            Main.error("nogreatsuccess");
            String expectedOutput;
            // Output den du erwartest
            expectedOutput = ConsoleColors.RED_BOLD + "nogreatsuccess" + ConsoleColors.RESET + "\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);

        }
    }
    @Test
    void handleBadIndexTest() {
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        List testList = new List("Test");
        testList.setListToDos(new ToDoItem[3]);

        try {
            System.setIn(new ByteArrayInputStream("y\n1\n".getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            int index = Main.handleBadIndex("Test Message.");
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
            int index = Main.handleBadIndex("Test Message.");
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

}
