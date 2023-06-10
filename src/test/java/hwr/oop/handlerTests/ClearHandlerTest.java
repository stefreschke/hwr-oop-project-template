package hwr.oop.handlerTests;

import hwr.oop.*;
import hwr.oop.handler.ClearHandler;
import hwr.oop.handler.ListHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static hwr.oop.handler.ClearHandler.clear;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClearHandlerTest {
    @Test
    void clearHandlerTest(){
        ClearHandler clearHandler = new ClearHandler();
        assertThat(clearHandler).isNotNull();
    }
    @Test
    void handleUserCommandTest() {
        PrintStream sysOutBackup = System.out;
        ToDoList list = new ToDoList("MyList");
        list.add(new ToDoItem("Apple", "Computers", new Bucket("Fruit"), Priority.MEDIUM, LocalDate.now()));
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            assertThat(list.getItems()).hasSize(1);
            ClearHandler.handleUserCommand(list, new ConsoleUserInterface(new PrintStream(outBuffer), System.in), new String[]{"gtd", "clear"});
            assertThat(list.getItems()).isNull();
        } finally {
            System.setOut(sysOutBackup);
        }
    }
    @Test
    void clearTest() {
        PrintStream sysOutBackup = System.out;
        ToDoList list = new ToDoList("MyList");
        list.add(new ToDoItem("Apple", "Computers", new Bucket("Fruit"), Priority.MEDIUM, LocalDate.now()));
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            assertThat(list.getItems()).hasSize(1);
            clear(list);
            assertThat(list.getItems()).isNull();
            // Check the program output
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
            ListHandler.list(list, testConsole);
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
