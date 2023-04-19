package hwr.oop.group4.todo.ui;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DialogHelperTest {

    private String retrieveResultFrom(OutputStream outputStream) {
        return outputStream.toString();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void canGetId() {
        Scanner inputStream = new Scanner(createInputStreamForInput("a\n13\n4\n"));
        OutputStream outputStream = new ByteArrayOutputStream();
        DialogHelper dialogHelper = new DialogHelper(new PrintStream(outputStream), inputStream);

        int returnValue = dialogHelper.getValidIdFromUser("This is a prompt.", 12);
        String output = retrieveResultFrom(outputStream);

        assertThat(returnValue).isEqualTo(4);
        assertThat(output).isEqualTo("This is a prompt.Invalid index. (Must be a number)\n" +
                "This is a prompt.Invalid index. (Must be between 0 and 12)\n" +
                "This is a prompt.");
    }

    @Test
    void canPrintAMenu() {
        Scanner inputStream = new Scanner(createInputStreamForInput("a\nadd\n"));
        OutputStream outputStream = new ByteArrayOutputStream();
        DialogHelper dialogHelper = new DialogHelper(new PrintStream(outputStream), inputStream);

        String message = "This is a message.";
        String inputPrefix = "input> ";

        Map<String, String> options = new HashMap<String, String>();
        options.put("add", "add something");
        options.put("delete", "delete something");

        String returnValue = dialogHelper.getMenuSelectionFromUser(message, inputPrefix, options);
        String output = retrieveResultFrom(outputStream);

        assertThat(returnValue).isEqualTo("add");
        assertThat(output).isEqualTo( "This is a message.\n" +
                "                           add - add something\n" +
                "                        delete - delete something\n" +
                "input> input> ");
    }

}
