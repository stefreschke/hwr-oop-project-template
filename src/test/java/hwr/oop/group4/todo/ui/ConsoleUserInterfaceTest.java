package hwr.oop.group4.todo.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConsoleUserInterfaceTest {

    private String retrieveResultFrom(OutputStream outputStream) {
        return outputStream.toString();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void constructUI() {
        InputStream inputStream = createInputStreamForInput("ew" + System.lineSeparator() + "new" + System.lineSeparator() );
        OutputStream outputStream = new ByteArrayOutputStream();
        ConsoleUserInterface ui = new ConsoleUserInterface(outputStream, inputStream);
        String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("Do want to 'load' from file or create 'new' list?"+System.lineSeparator() +
                "Please enter 'new' or 'load'." + System.lineSeparator());
    }
}