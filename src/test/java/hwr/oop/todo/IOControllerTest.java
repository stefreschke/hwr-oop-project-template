package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IOControllerTest {

    @Test
    void canPrintPrompt(){
        OutputStream outputStream = new ByteArrayOutputStream();

        IOController controller = new IOController(System.in, outputStream);
        controller.printPrompt("string");

        String output = retrieveResultFrom(outputStream);

        assertTrue(output.contains("string"));
        assertTrue(output.contains(System.lineSeparator()));
    }

    @Test
    void canPrintPromptList(){
        InputStream inputStream = createInputStreamForInput("");
        OutputStream outputStream = new ByteArrayOutputStream();

        IOController controller = new IOController(inputStream, outputStream);
        List<MenuOption> options= List.of(new MenuOption('a', "Example action to test very long"), new MenuOption('b', "Example action"));
        controller.printPrompt(options);
    }

    @Test
    void canGetInputString(){
        OutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = createInputStreamForInput("AAA\n");
        IOController controller = new IOController(inputStream, output);

        String input = controller.getInputString();

        assertEquals("AAA", input);
    }

    @Test
    void canGetInputInt(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = createInputStreamForInput("3");
        IOController controller = new IOController(inputStream, out);
        int input = controller.getInputInt();

        assertEquals(3, input);
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
