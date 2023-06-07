package hwr.oop.handlerTests;

import hwr.oop.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static hwr.oop.handler.ClearHandler.clear;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearHandlerTest {
    @Test
    void clearTest() {
        PrintStream sysOutBackup = System.out;
        ToDoList list = new ToDoList("MyList");
        list.add(new ToDoItem("Apple", "Computers", new Bucket("Fruit"), Priority.MEDIUM));
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            assertThat(list.getItems()).hasSize(1);
            clear(list);
            assertThat(list.getItems()).isNull();
            // Check the program output
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            testConsole.list(list);
            String expectedOutput;
            expectedOutput = "MyList:\n" +
                    "👀Looks Empty here... Add some tasks!\n";
            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(sysOutBackup);
        }
    }
}
