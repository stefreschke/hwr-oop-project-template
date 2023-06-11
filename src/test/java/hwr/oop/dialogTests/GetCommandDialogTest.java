package hwr.oop.dialogTests;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ToDoList;
import hwr.oop.dialog.GetCommandDialog;
import hwr.oop.handler.CommandParser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetCommandDialogTest {
    @Test
    void testStart() throws Exception {
        ToDoList toDoList = new ToDoList("testList");
        String userInput = "gtd help\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream(userInput.getBytes()));
        CommandParser commandParser = new CommandParser(cui);
        GetCommandDialog getCommandDialog = new GetCommandDialog(toDoList, cui, commandParser);
        assertEquals(0, getCommandDialog.start());
    }
    @Test
    void testGetCommand() throws Exception {
        ToDoList toDoList = new ToDoList("testList");
        String userInput = "gtd help\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream(userInput.getBytes()));
        CommandParser commandParser = new CommandParser(cui);
        GetCommandDialog getCommandDialog = new GetCommandDialog(toDoList, cui, commandParser);
        assertEquals(0, getCommandDialog.getCommand(toDoList));
    }
    @Test
    void testReadCommand() throws Exception {
        ToDoList toDoList = new ToDoList("testList");
        String userInput = "test\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream(userInput.getBytes()));
        CommandParser commandParser = new CommandParser(cui);
        GetCommandDialog getCommandDialog = new GetCommandDialog(toDoList, cui, commandParser);
        assertEquals("test", getCommandDialog.readCommand());
    }
    @Test
    void testCallHandler() throws Exception {
        ToDoList toDoList = new ToDoList("testList");
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(new ByteArrayOutputStream()), System.in);
        CommandParser commandParser = new CommandParser(cui);
        GetCommandDialog getCommandDialog = new GetCommandDialog(toDoList, cui, commandParser);
        assertEquals(0, getCommandDialog.callHandler(toDoList, new String[]{"gtd", "help"}));
        assertEquals(1, getCommandDialog.callHandler(toDoList, new String[]{"gtd", "remove 0"}));
        assertEquals(0, getCommandDialog.callHandler(toDoList, new String[]{"gtd", "list"}));
        assertEquals(0, getCommandDialog.callHandler(toDoList, new String[]{"gtd", "h"}));
        assertEquals(0, getCommandDialog.callHandler(toDoList, new String[]{"gtd", "ls"}));
        assertEquals(1, getCommandDialog.callHandler(toDoList, new String[]{"gtd", "gutentag list"}));
        assertEquals(1, getCommandDialog.callHandler(toDoList, new String[]{"gtd", "whatever"}));

    }
    @Test
    void testCouldNotReadCommandException() {
        GetCommandDialog.CouldNotreadCommandException couldNotreadCommandException = new GetCommandDialog.CouldNotreadCommandException("test");
        assertEquals("test", couldNotreadCommandException.getMessage());
    }
}
