package hwr.oop.dialogTests;

import hwr.oop.*;
import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.dialog.EditDialog;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class EditDialogTest {
    public static final ToDoItem toDoItem = new ToDoItem("MyItem", "MyItem",  new Bucket("MyItem"), Priority.HIGH, LocalDate.now());
    public static final ToDoList toDoList = new ToDoList("MyList", "test.json");
    @Test
    void getTitleForEditTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                    new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String title = new EditDialog(testConsole, toDoList).getTitleForEdit(toDoItem);
            assertThat(title).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getTitleForEditEmptyInputTest(){
        try {
            String userInput = "\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                    new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String title = new EditDialog(testConsole, toDoList).getTitleForEdit(toDoItem);
            assertThat(title).isEqualTo(toDoItem.getTitle());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getDescriptionForEditTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                    new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String desc = new EditDialog(testConsole, toDoList).getDescriptionForEdit(toDoItem);
            assertThat(desc).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getDescriptionForEditEmptyInputTest(){
        try {
            String userInput = "\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                    new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String desc = new EditDialog(testConsole, toDoList).getDescriptionForEdit(toDoItem);
            assertThat(desc).isEqualTo(toDoItem.getDescription());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getPriorityForEditTest(){
        try {
            String userInput = "3\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                    new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            Priority prio = new EditDialog(testConsole, toDoList).getPriorityForEdit(toDoItem);
            assertThat(prio).isEqualTo(Priority.HIGH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getPriorityForEditEmptyInputTest(){
        try {
            String userInput = "\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                    new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            Priority prio = new EditDialog(testConsole, toDoList).getPriorityForEdit(toDoItem);
            assertThat(prio).isEqualTo(toDoItem.getPriority());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getBucketForEditTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                    new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            Bucket bucket = new EditDialog(testConsole, toDoList).getBucketForEdit(toDoItem);
            assertThat(bucket.getBucketName()).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getBucketForEditEmptyInputTest(){
        try {
            String userInput = "\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                    new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            Bucket bucket = new EditDialog(testConsole, toDoList).getBucketForEdit(toDoItem);
            assertThat(bucket).isEqualTo(toDoItem.getBucket());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void startTest() throws ConsoleUserInterface.CouldNotReadInputException {
        String userInput = "MyItem\nDesc\n1\nMyBucket\n1.1.2020\n";
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoItem newItem = new EditDialog(testConsole, toDoList).start(toDoItem, 0);
        assertThat(newItem).hasToString(new ToDoItem("MyItem", "Desc", new Bucket("MyBucket"), Priority.LOW, LocalDate.of(2020,1,1)).toString());
    }
    @Test
    void endTest() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        new EditDialog(testConsole, toDoList).end();
        assertThat(outBuffer).hasToString("\u001B[1;32mTask Edited Successfully!\u001B[0m\n");
    }
}
