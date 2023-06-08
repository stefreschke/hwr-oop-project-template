package hwr.oop.handlerTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;
import hwr.oop.handler.HelpHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class HelpHandlerTest {
    @Test
    void handleUserCommand() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ToDoList toDoList = new ToDoList("MyToDoList");
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
                "[sort, s]\u001B[1;35m[priority | createdAt | bucket | title | done] [asc | desc] \u001B[0m - \u001B[1;34msort your tasks\u001B[0m\n" +
                "[showBuckets, sb]\u001B[1;35m\u001B[0m - \u001B[1;34mshow buckets for tasks\u001B[0m\n" +
                "[renameBucket, rnb]\u001B[1;35m[index] \u001B[0m - \u001B[1;34mchanges bucket name\u001B[0m\n" +
                "[clear, cls]\u001B[1;35m\u001B[0m - \u001B[1;34mclear all tasks\u001B[0m\n" +
                "[exit, q]\u001B[1;35m\u001B[0m - \u001B[1;34mexit the program\u001B[0m\n";
        HelpHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "help"});

        assertThat(outBuffer).hasToString(expected);
    }
}
