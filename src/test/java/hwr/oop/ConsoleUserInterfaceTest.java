package hwr.oop;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleUserInterfaceTest {

    @Test
    void consoleUI_TypeThreeAndFour_OutputIsSeven() {
        // given
        InputStream inputStream = createInputStreamForInput("3\n4\n");
        OutputStream outputStream = new ByteArrayOutputStream();
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(outputStream, inputStream);
        // when
        consoleUI.start();
        // then
        String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("7");
    }

    private String retrieveResultFrom(OutputStream outputStream) {
        String outputText = outputStream.toString();
        String key = "output:";
        return outputText.substring(outputText.indexOf(key) + key.length()).trim();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }
}
