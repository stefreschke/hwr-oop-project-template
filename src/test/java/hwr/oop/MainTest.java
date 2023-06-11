package hwr.oop;

import hwr.oop.dialog.GetCommandDialog;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
class MainTest {
    @Test
    void mainTest() {
        //overwrite System.in with a String
        String input = "gtd exit";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        //overwrite System.out with a buffer
        java.io.ByteArrayOutputStream outBuffer = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outBuffer));
        try {
            Main.main(new String[0]);
        } catch (GetCommandDialog.CouldNotreadCommandException e) {
            assertThat(e.getMessage()).isEqualTo("Could not read command");
        }
        assertThat(outBuffer).hasToString("\u001B[1;34mPlease enter a command or type 'gtd help' for more information\u001B[0m\n" +
                "> Exiting ...\n" +
                "Goodbye!\n");

        System.setIn(System.in);
        System.setOut(System.out);
    }
}
