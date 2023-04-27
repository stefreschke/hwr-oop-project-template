package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOControllerTest {

    @Test
    void canPrintPropmptString(){
        IOController controller = new IOController(System.in);
        controller.printPrompt("string");
    }

    @Test
    void canPrintPropmptList(){
        IOController controller = new IOController(System.in);
        List<MenuOption> options= List.of(new MenuOption('a', "Example action to test very long"), new MenuOption('b', "Example action"));
        controller.printPrompt(options);
    }

    @Test
    void canGetInputString(){
        InputStream inputStream = createInputStreamForInput("AAA\n");
        IOController controller = new IOController(inputStream);
        String input = controller.getInputString();

        assertEquals("AAA", input);
    }

    @Test
    void canGetInputInt(){
        InputStream inputStream = createInputStreamForInput("3");
        IOController controller = new IOController(inputStream);
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
