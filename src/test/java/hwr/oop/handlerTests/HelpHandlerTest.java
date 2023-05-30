package hwr.oop.handlerTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.handler.HelpHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class HelpHandlerTest {
    @Test
    void handleUserCommand() {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        String expected = "gtd [command] [arguments]\n" +
                "Commands:\n" +
                "  help                            -  print this help\n" +
                "  add [Item Index]                -  add a new task\n" +
                "  remove [Item Index]             -  remove a task\n" +
                "  promote [Item Index]            -  promote a task to a further state\n" +
                "  demote [Item Index]             -  demote a task to a previous state\n" +
                "  onhold [Item Index]             -  put a task on hold\n" +
                "  done [Item Index]               -  mark a task as done\n" +
                "  edit [Item Index]               -  edit a task\n" +
                "  list                            -  list all tasks\n" +
                "  sort                            -  sort your tasks\n" +
                "  createBucket [Name]             -  create a bucket for tasks\n" +
                "  showBuckets                     -  show buckets for tasks\n" +
                "  renameBucket [index] [Name]     -  changes bucket name\n" +
                "  clear                           -  clear all tasks\n" +
                "  exit                            -  exit the program\n";
        HelpHandler.handleUserCommand(null, cui, null);

        assertThat(outBuffer.toString()).isEqualTo(expected);
    }
}
