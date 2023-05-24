package hwr.oop;

import hwr.oop.util.ConsoleColors;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Set;

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
    void helpTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            cui.help();
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
                    "  list                -  list all tasks\n" +
                    "  createBucket [bucket name]      -  create a bucket for tasks\n" +
                    "  editBuckets [index] [new name]  -  changes bucket name\n" +
                    "  showBuckets                     -  show buckets for tasks \n" +
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
    void addNoFileSpecifiedTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            String userInput = "Title\nDescription\n3\nBucket\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ToDoList toDoList = new ToDoList("MyList");
            cui.add(toDoList);
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "Create a new task\n" +
                    "Please enter a title for your task\n" +
                    "Please enter a description for your task\n" +
                    "Please enter a priority for your task\n" +
                    "\u001B[1;34m1 - LOW, \u001B[1;33m2 - MEDIUM, \u001B[1;31m3 - HIGH\u001B[0m\n" +
                    "Add a Tag to group your tasks\n" +
                    "\u001B[1;32mTask Created Successfully!\u001B[0m\n" +
                    "Could not save your progress... please specify a file or try again.\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("Title");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void addFileSpecifiedTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            String userInput = "Title\nDescription\n3\nTag\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ToDoList toDoList = new ToDoList("MyList", "addTestFile");
            cui.add(toDoList);
            // Check the program output
            String expectedOutput;
            expectedOutput =
                    "Create a new task\n" +
                        "Please enter a title for your task\n" +
                        "Please enter a description for your task\n" +
                        "Please enter a priority for your task\n" +
                        "\u001B[1;34m1 - LOW, \u001B[1;33m2 - MEDIUM, \u001B[1;31m3 - HIGH\u001B[0m\n" +
                        "Add a Bucket to group your tasks\n" +
                        "\u001B[1;32mTask Created Successfully!\u001B[0m\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("Title");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void editNotFileSpecifiedTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        try {
            String userInput = "MyList\nDescription\n3\nBucket\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ToDoList toDoList = new ToDoList("MyList");
            toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
            cui.edit(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Editing task at index 0:\n" +
                    "⏭\uFE0F Test\n" +
                    "Test\n" +
                    "<\u001B[1;36mTest\u001B[0m> \u001B[1;34mLOW\u001B[0m\n" +
                    "Enter new Title or press enter to skip\n" +
                    "Enter new Description or press enter to skip\n" +
                    "Enter new Priority or press enter to skip\n" +
                    "1 - LOW, 2 - MEDIUM, 3 - HIGH\n" +
                    "Enter new Bucket or press enter to skip\n" +
                    "Task Edited Successfully!\n" +
                    "Could not save your progress... please specify a file or try again.\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("MyList");
            assertThat(toDoList.getItems()[0].getDescription()).isEqualTo("Description");
            assertThat(toDoList.getItems()[0].getPriority()).isEqualTo(Priority.HIGH);
            assertThat(toDoList.getItems()[0].getBucket()).isEqualTo("Tag");
            assertThat(env).isNotNull();

        } catch (ConsoleUserInterface.CouldNotReadInputException e) {
            throw new RuntimeException(e);
        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    void editFileSpecifiedTest() {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        try {
            String userInput = "MyList\nDescription\n3\nTag\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ToDoList toDoList = new ToDoList("MyList", "editTestFile");
            toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
            toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
            cui.edit(toDoList, 0);
            // Check the program output
            String expectedOutput;
            expectedOutput = "Editing task at index 0:\n" +
                    "⏭\uFE0F Test\n" +
                    "Test\n" +
                    "<\u001B[1;36mTest\u001B[0m> \u001B[1;34mLOW\u001B[0m\n" +
                    "Enter new Title or press enter to skip\n" +
                    "Enter new Description or press enter to skip\n" +
                    "Enter new Priority or press enter to skip\n" +
                    "1 - LOW, 2 - MEDIUM, 3 - HIGH\n" +
                    "Enter new Bucket or press enter to skip\n" +
                    "Task Edited Successfully!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
            assertThat(toDoList.getItems()[0].getTitle()).isEqualTo("MyList");
            assertThat(toDoList.getItems()[0].getDescription()).isEqualTo("Description");
            assertThat(toDoList.getItems()[0].getPriority()).isEqualTo(Priority.HIGH);
            assertThat(toDoList.getItems()[0].getBucket()).isEqualTo("Bucket");
            assertThat(env).isNotNull();

        } catch (ConsoleUserInterface.CouldNotReadInputException e) {
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
    void doneTest() {
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "listTest.json");
        toDoList.add(new ToDoItem("Test", "Test", "Test", Priority.LOW));
        assertThat(toDoList.getItems()[0].isDone()).isFalse();
        cui.done(toDoList, 0);
        assertThat(toDoList.getItems()[0].isDone()).isTrue();
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
    void clearTest() {
        PrintStream sysOutBackup = System.out;
        ToDoList toDoList = new ToDoList("MyList");
        toDoList.add(new ToDoItem("Apple", "Computers", "Fruit", Priority.MEDIUM));
        try {
            assertThat(toDoList.getItems()).hasSize(1);
            Main.clear(toDoList);
            assertThat(toDoList.getItems()).isNull();
        } finally {
            System.setOut(sysOutBackup);
        }
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
            expectedOutput = "exiting...\n";
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

    @Test
    void createBucketsTest() throws ConsoleUserInterface.CouldNotReadInputException {
        PrintStream sysOutBackup = System.out;
        InputStream sysInBackup = System.in;

        ToDoList toDoList = new ToDoList("My ToDoList");
        try {
            String userInput = "Title\nDescription\n12.12.12\n3\nBucket\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            cui.add(toDoList);
            Set<Bucket> testBuckets = toDoList.getBuckets();
            assertThat(testBuckets.contains(new Bucket("Bucket"))).isTrue();

        }finally {
            System.setOut(sysOutBackup);
            System.setIn(sysInBackup);
        }

    }

    @Test
    void showBucketsTest() {
        PrintStream sysOutBackup = System.out;
        InputStream sysInBackup = System.in;

        ToDoList toDoList = new ToDoList("My ToDoList");
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        try {
            String userInput = "Title\nDescription\n12.12.12\n3\nBucket\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            cui.add(toDoList);
            String userInput2 = "Title\nDescription\n12.12.12\n3\nBucket2\n";
            System.setIn(new ByteArrayInputStream(userInput2.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer2 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer2));
            cui.add(toDoList);
            String userInput3 = "Title\nDescription\n12.12.12\n3\nBucket3\n";
            System.setIn(new ByteArrayInputStream(userInput3.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer3 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer3));
            cui.add(toDoList);
            Set<Bucket> TestBuckets = toDoList.getBuckets();
            assertThat(TestBuckets).isEqualTo(toDoList.getBuckets());
        } catch (ConsoleUserInterface.CouldNotReadInputException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(sysOutBackup);
            System.setIn(sysInBackup);
        }

    }

    @Test
    void editBucketTest() {
        PrintStream sysOutBackup = System.out;
        InputStream sysInBackup = System.in;

        ToDoList toDoList = new ToDoList("My ToDoList");
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        try {
            String userInput = "Title\nDescription\n12.12.12\n3\nBucket\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));
            toDoList.addBucket("Bucket");
            cui.add(toDoList);
            toDoList.editBucket(0, "Test");
            assertThat(toDoList.getBuckets().contains(new Bucket("Test"))).isTrue();
        } catch (ConsoleUserInterface.CouldNotReadInputException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(sysOutBackup);
            System.setIn(sysInBackup);
        }
    }
}

// Type cast to List
