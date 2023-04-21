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
        InputStream inputStream = createInputStreamForInput("\nquit\n");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStream));
        System.setIn(inputStream);

        TodoUiApplication.main(new String[] {});

        System.setOut(System.out);
        System.setIn(System.in);

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
            "Do you want to load from a file? (Otherwise create an empty todo list)\n" +
            "Answer y/Y/yes or n/N/no (leave empty for: no): Main Menu\n" +
            "                        intray - \n" +
            "                         tasks - \n" +
            "                      projects - \n" +
            "                      calendar - \n" +
            "                          load - \n" +
            "                          save - \n" +
            "                          quit - Quit the program.\n" +
            "main> ");
    }

}
