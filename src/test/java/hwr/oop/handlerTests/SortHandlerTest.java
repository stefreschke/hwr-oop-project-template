package hwr.oop.handlerTests;

import hwr.oop.*;
import hwr.oop.handler.SortHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SortHandlerTest {
    @Test
    void sortHandlerTest() {
        SortHandler sortHandler = new SortHandler();
        assertThat(sortHandler).isNotNull();
    }
    @Test
    void sortHelpTest(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"gtd", "sort", "help"};
        SortHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer.toString()).hasToString("gtd sort [option]\n" +
                "Options:\n" +
                "  priority - sort by priority\n" +
                "  createdAt- sort by creation date\n" +
                "  dueDate  - sort by due date\n" +
                "  bucket [bucket]- sort by bucket\n" +
                "  title    - sort by title\n" +
                "  done     - sort by done\n" +
                "  help     - print this help\n");
    }
    @Test
    void sortHelpTooLittleArgsTest(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"gtd", "sort"};
        SortHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer).hasToString("gtd sort [option]\n" +
                "Options:\n" +
                "  priority - sort by priority\n" +
                "  createdAt- sort by creation date\n" +
                "  dueDate  - sort by due date\n" +
                "  bucket [bucket]- sort by bucket\n" +
                "  title    - sort by title\n" +
                "  done     - sort by done\n" +
                "  help     - print this help\n");
    }
    @Test
    void sortHelpWrongArgsTest(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        String[] args = {"gtd", "sort", "prierity", "vertically"};
        SortHandler.handleUserCommand(toDoList, cui, args);
        assertThat(outBuffer.toString()).hasToString("gtd sort [option]\n" +
                "Options:\n" +
                "  priority - sort by priority\n" +
                "  createdAt- sort by creation date\n" +
                "  dueDate  - sort by due date\n" +
                "  bucket [bucket]- sort by bucket\n" +
                "  title    - sort by title\n" +
                "  done     - sort by done\n" +
                "  help     - print this help\n");
    }
    @Test
    void handleUserCommandPrioAscTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        Bucket bucket1 = new Bucket("Bucket 1");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        String[] args = {"gtd", "sort", "prio", "asc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 2", "Task 1", "Task 3", "Task 4"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandPrioDescTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        Bucket bucket1 = new Bucket("Bucket 1");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        String[] args = {"gtd", "sort", "prio", "desc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 4", "Task 1", "Task 3", "Task 2"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandCreatedAtAscTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        Bucket bucket1 = new Bucket("Bucket 1");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        toDoList.getItems().get(0).setCreatedAt(LocalDateTime.now().minusHours(2));
        toDoList.getItems().get(1).setCreatedAt(LocalDateTime.now().minusHours(5));
        toDoList.getItems().get(2).setCreatedAt(LocalDateTime.now().minusHours(1));
        toDoList.getItems().get(3).setCreatedAt(LocalDateTime.now().minusHours(7));
        String[] args = {"gtd", "sort", "create", "asc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 4", "Task 2", "Task 1", "Task 3"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandCreatedAtDescTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        Bucket bucket1 = new Bucket("Bucket 1");
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        toDoList.getItems().get(0).setCreatedAt(LocalDateTime.now().minusHours(2));
        toDoList.getItems().get(1).setCreatedAt(LocalDateTime.now().minusHours(5));
        toDoList.getItems().get(2).setCreatedAt(LocalDateTime.now().minusHours(1));
        toDoList.getItems().get(3).setCreatedAt(LocalDateTime.now().minusHours(7));
        String[] args = {"gtd", "sort", "create", "desc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 3", "Task 1", "Task 2", "Task 4"});
        // Add more assertions to verify the behavior of the add command
    }

    @Test
    void handleUserCommandTitleAscTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        Bucket bucket1 = new Bucket("Bucket 1");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        String[] args = {"gtd", "sort", "title", "asc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 1", "Task 2", "Task 3", "Task 4"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandTitleDescTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        Bucket bucket1 = new Bucket("Bucket 1");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        String[] args = {"gtd", "sort", "title", "desc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 4", "Task 3", "Task 2", "Task 1"});
        // Add more assertions to verify the behavior of the add command
    }

    @Test
    void handleUserCommandDoneAscTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        Bucket bucket1 = new Bucket("Bucket 1");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        toDoList.getItems().get(1).setDone();
        String[] args = {"gtd", "sort", "done", "asc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 1", "Task 3", "Task 4", "Task 2"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandDoneDescTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        Bucket bucket1 = new Bucket("Bucket 1");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        toDoList.getItems().get(1).setDone();
        String[] args = {"gtd", "sort", "done", "desc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 2", "Task 1", "Task 3", "Task 4"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandDueDateAscTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        Bucket bucket1 = new Bucket("Bucket 1");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now().plusDays(1)));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now().plusDays(12)));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        String[] args = {"gtd", "sort", "due", "asc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 2", "Task 4", "Task 1", "Task 3"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandDueDateDescTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        Bucket bucket1 = new Bucket("Bucket 1");
        toDoList.add(new ToDoItem("Task 1", "Description 1", bucket1, Priority.MEDIUM, LocalDate.now().plusDays(1)));
        toDoList.add(new ToDoItem("Task 2", "Description 2", bucket1, Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", bucket1, Priority.MEDIUM, LocalDate.now().plusDays(12)));
        toDoList.add(new ToDoItem("Task 4", "Description 4", bucket1, Priority.HIGH, LocalDate.now()));
        String[] args = {"gtd", "sort", "due", "desc"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 3", "Task 1", "Task 2", "Task 4"});
        // Add more assertions to verify the behavior of the add command
    }
    @Test
    void handleUserCommandBucketTest() {
        // Arrange
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        String userInput = "Task 1\nDescription 1\nBucket 1\nLOW\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new ToDoList("MyList", "test.json");
        toDoList.add(new ToDoItem("Task 1", "Description 1", new Bucket("Bucket 1"), Priority.MEDIUM, LocalDate.now().plusDays(1)));
        toDoList.add(new ToDoItem("Task 2", "Description 2", new Bucket("Bucket 2"), Priority.LOW, LocalDate.now()));
        toDoList.add(new ToDoItem("Task 3", "Description 3", new Bucket("Bucket 3"), Priority.MEDIUM, LocalDate.now().plusDays(12)));
        toDoList.add(new ToDoItem("Task 4", "Description 4", new Bucket("Bucket 1"), Priority.HIGH, LocalDate.now()));
        String[] args = {"gtd", "sort", "bucket", "Bucket 1"};

        // Act
        SortHandler.handleUserCommand(toDoList, cui, args);
        String[] titles = new String[4];
        for (int i = 0; i < 4; i++) {
            titles[i] = toDoList.getItems().get(i).getTitle();
        }
        // Assert
        assertThat(titles).isEqualTo(new String[]{"Task 1", "Task 4", "Task 2", "Task 3"});
        // Add more assertions to verify the behavior of the add command
    }
}
