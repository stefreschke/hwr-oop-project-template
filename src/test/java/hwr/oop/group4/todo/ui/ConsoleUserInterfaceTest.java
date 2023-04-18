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

    // source: https://gist.github.com/stefreschke/b12bfffff75c50daf4c4109b88cd6d5b
    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void helloName() {
        InputStream inputStream = createInputStreamForInput("Peter\n");
        OutputStream outputStream = new ByteArrayOutputStream();
        ConsoleUserInterface ui = new ConsoleUserInterface(outputStream, inputStream);
        ui.helloName();
        String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("Who are you?" + System.lineSeparator() + "Hello Peter!" + System.lineSeparator());
    }
}