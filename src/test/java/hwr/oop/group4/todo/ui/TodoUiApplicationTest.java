package hwr.oop.group4.todo.ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TodoUiApplicationTest {

    private String retrieveResultFrom(OutputStream outputStream) {
        return outputStream.toString();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void canStartAndQuitMainMenu() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() + "quit" + System.lineSeparator());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStream));
        System.setIn(inputStream);

        TodoUiApplication.main(new String[] {});

        System.setOut(System.out);
        System.setIn(System.in);

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
            "Do you want to load from a file? (Otherwise create an empty todo list)" + System.lineSeparator() +
            "Answer y/Y/yes or n/N/no (leave empty for: no): Main Menu" + System.lineSeparator() +
            "                        intray - " + System.lineSeparator() +
            "                         tasks - " + System.lineSeparator() +
            "                      projects - " + System.lineSeparator() +
            "                      calendar - " + System.lineSeparator() +
            "                          load - " + System.lineSeparator() +
            "                          save - " + System.lineSeparator() +
            "                          quit - Quit the program." + System.lineSeparator() +
            "main> ");
    }

}
