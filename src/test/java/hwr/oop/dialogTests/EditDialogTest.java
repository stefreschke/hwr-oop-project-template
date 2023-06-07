package hwr.oop.dialogTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.Priority;
import hwr.oop.ToDoItem;
import hwr.oop.dialog.EditDialog;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class EditDialogTest {
    public static final ToDoItem toDoItem = new ToDoItem("MyItem", "MyItem",  "MyItem", Priority.HIGH);
    @Test
    void getTitleForEditTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                    new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String title = new EditDialog(testConsole).getTitleForEdit(toDoItem);
            assertThat(title).isEqualTo("MyItem");
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
            String desc = new EditDialog(testConsole).getDescriptionForEdit(toDoItem);
            assertThat(desc).isEqualTo("MyItem");
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
            Priority prio = new EditDialog(testConsole).getPriorityForEdit(toDoItem);
            assertThat(prio).isEqualTo(Priority.HIGH);
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
            String bucket = new EditDialog(testConsole).getBucketForEdit(toDoItem);
            assertThat(bucket).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void startTest() throws ConsoleUserInterface.CouldNotReadInputException {
        String userInput = "MyItem\nDesc\n1\nMyBucket\n";
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer),
                new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoItem newItem = new EditDialog(testConsole).start(toDoItem, 0);
        assertThat(newItem).hasToString(new ToDoItem("MyItem", "Desc", "MyBucket", Priority.LOW).toString());
    }
}
