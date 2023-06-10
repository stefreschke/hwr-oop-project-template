package hwr.oop.dialogTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.dialog.HandleBadIndexDialog;
import hwr.oop.util.ConsoleColors;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandleBadIndexDialogTest {
    @Test
    void handleBadIndexTest() {
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("y\n1\n".getBytes(StandardCharsets.UTF_8)));
            int index =  new HandleBadIndexDialog(testConsole).start("Test Message.");
            // Check the program output
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "There is nothing at that index... \uD83E\uDD78" + ConsoleColors.RESET + "\n" +
                    "Try again? (y/n)\n" +
                    "Test Message.\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
            assertThat(index).isEqualTo(1);
            System.setIn(System.in);
        }
        catch (Exception e) {
            System.setIn(System.in);
            throw new RuntimeException(e);
        }
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ConsoleUserInterface testConsole = new ConsoleUserInterface(new PrintStream(outBuffer), new ByteArrayInputStream("n\n".getBytes(StandardCharsets.UTF_8)));
            int index = new HandleBadIndexDialog(testConsole).start("Test Message.");
            // Check the program output
            String expectedOutput;
            expectedOutput = ConsoleColors.RED_BOLD + "There is nothing at that index... \uD83E\uDD78" + ConsoleColors.RESET + "\n" +
                    "Try again? (y/n)\n" +
                    "Okay, I'll leave you alone then. 👋\n";
            String actualOutput = outBuffer.toString().replace("\r", "");
            assertEquals(expectedOutput, actualOutput);
            assertThat(index).isEqualTo(-1);
            System.setIn(System.in);
        }
        catch (Exception e) {
            System.setIn(System.in);
            throw new RuntimeException(e);
        }
    }
}
