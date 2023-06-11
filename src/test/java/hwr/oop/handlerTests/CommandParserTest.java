package hwr.oop.handlerTests;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ToDoList;
import hwr.oop.handler.CommandParser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandParserTest {
    @Test
    void getCommandsTest() {
        assertThat(CommandParser.CommandHandler.EDIT.getCommands()).containsExactly("edit", "e");
    }
    @Test
    void getHandlerClassTest() {
        assertThat(CommandParser.CommandHandler.EDIT.getHandlerClass()).isInstanceOf(hwr.oop.handler.HandlerCommandsInterface.class);
    }
    @Test
    void handleWrongCommandTest() throws CommandParser.CouldNotCallHandlerException {
        String[] args = {"", "wrongCommand"};
        ToDoList toDoList = new ToDoList("MyList", "testFile.json");
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        String expected = "Here is a list of all commands:\n" +
                "gtd [command] [arguments]\n" +
                "\n" +
                "[help, h]\u001B[1;35m\u001B[0m - \u001B[1;34mprint this help\u001B[0m\n" +
                "[add, a]\u001B[1;35m\u001B[0m - \u001B[1;34madd a new task\u001B[0m\n" +
                "[remove, rm]\u001B[1;35m[index] \u001B[0m - \u001B[1;34mremove a task\u001B[0m\n" +
                "[promote, p]\u001B[1;35m[index] \u001B[0m - \u001B[1;34mpromote a task to a further state\u001B[0m\n" +
                "[demote, d]\u001B[1;35m[index] \u001B[0m - \u001B[1;34mdemote a task to a previous state\u001B[0m\n" +
                "[hold, hd]\u001B[1;35m[index] \u001B[0m - \u001B[1;34mput a task on hold\u001B[0m\n" +
                "[done, do]\u001B[1;35m[index] \u001B[0m - \u001B[1;34mmark a task as done\u001B[0m\n" +
                "[edit, e]\u001B[1;35m[index] \u001B[0m - \u001B[1;34medit a task\u001B[0m\n" +
                "[list, ls]\u001B[1;35m\u001B[0m - \u001B[1;34mlist all tasks\u001B[0m\n" +
                "[sort, s]\u001B[1;35m[priority | estimatedTime | createdAt | bucket | title | done] [asc | desc] \u001B[0m - \u001B[1;34msort your tasks\u001B[0m\n" +
                "[showBuckets, sb]\u001B[1;35m\u001B[0m - \u001B[1;34mshow buckets for tasks\u001B[0m\n" +
                "[renameBucket, rnb]\u001B[1;35m[index] \u001B[0m - \u001B[1;34mchanges bucket name\u001B[0m\n" +
                "[clear, cls]\u001B[1;35m\u001B[0m - \u001B[1;34mclear all tasks\u001B[0m\n" +
                "[exit, q]\u001B[1;35m\u001B[0m - \u001B[1;34mexit the program\u001B[0m\n";
        assertThat(new CommandParser(cui).handle(toDoList, args)).isEqualTo(1);
        assertThat(outBuffer.toString().replace("\r", "")).hasToString(expected);
    }
    @Test
    void handleCouldNotCallHandlerTest(){
        String[] args = {"", "help"};
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        try {
            new CommandParser(null).handle(null, args);
        } catch (CommandParser.CouldNotCallHandlerException e) {
            assertThat(e).hasMessage("Could not process your command. Please retry or restart the application.");
        }
    }
    @Test
    void handleCouldNotCallHandlerWrongCommandTest(){
        String[] args = {"", "wrongCommand"};
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        try {
            new CommandParser(null).handle(null, args);
        } catch (CommandParser.CouldNotCallHandlerException e) {
            assertThat(e).hasMessage("Could not process your command. Please retry or restart the application.");
        }
    }
    @Test
    void testCouldNotCallHandlerException() {
        CommandParser.CouldNotCallHandlerException couldNotCallHandlerException = new CommandParser.CouldNotCallHandlerException();
        assertEquals("Could not process your command. Please retry or restart the application.", couldNotCallHandlerException.getMessage());
    }
}
