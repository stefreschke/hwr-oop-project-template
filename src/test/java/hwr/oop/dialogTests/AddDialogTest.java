package hwr.oop.dialogTests;

import hwr.oop.Bucket;
import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.Priority;
import hwr.oop.ToDoList;
import hwr.oop.dialog.AddDialog;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class AddDialogTest {
    public static final ToDoList toDoList = new ToDoList("MyList", "test.json");
    @Test
    void getTitleForAddTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String title = new AddDialog(testConsole, toDoList).getTitleForAdd();
            assertThat(title).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getTitleForAddFailedInputTest(){
        try {
            String userInput = "";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String title = new AddDialog(testConsole, toDoList).getTitleForAdd();
            assertThat(title).isNotEqualTo("MyItem");
            assertThat(title).isEqualTo("NO TITLE");
            assertThat(outBuffer.toString().replace("\r", "")).hasToString("Please enter a title for your task\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getTitleForAddNoInputTest() {
        try {
            String userInput = "\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String title = new AddDialog(testConsole, toDoList).getTitleForAdd();
            assertThat(title).isNotEqualTo("MyItem");
            assertThat(title).isEqualTo("NO TITLE");
            assertThat(outBuffer.toString().replace("\r", "")).hasToString("Please enter a title for your task\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getDescriptionForAddTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String desc = new AddDialog(testConsole, toDoList).getDescriptionForAdd();
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
            Priority prio = new AddDialog(testConsole, toDoList).getPriorityForAdd();
            assertThat(prio).isEqualTo(Priority.HIGH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getBucketForAddTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            Bucket bucket = new AddDialog(testConsole, toDoList).getBucketForAdd();
            assertThat(bucket.getBucketName()).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
