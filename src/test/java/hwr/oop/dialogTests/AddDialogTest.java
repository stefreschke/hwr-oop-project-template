package hwr.oop.dialogTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.Priority;
import hwr.oop.dialog.AddDialog;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class AddDialogTest {
    @Test
    void getTitleForAddTest(){
        try {
            String userInput = "MyItem\n";
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            String title = new AddDialog(testConsole).getTitleForAdd();
            assertThat(title).isEqualTo("MyItem");
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
            String desc = new AddDialog(testConsole).getDescriptionForAdd();
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
            Priority prio = new AddDialog(testConsole).getPriorityForAdd();
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
            String bucket = new AddDialog(testConsole).getBucketForAdd();
            assertThat(bucket).isEqualTo("MyItem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
