package hwr.oop.group4.todo.ui;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TodoUiApplicationTest {

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

        TodoUiApplication.main(new String[0]);

        System.setOut(System.out);
        System.setIn(System.in);

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "Do you want to load from a file? (Otherwise create an empty todo list)" + System.lineSeparator() +
                "Answer y/Y/yes or n/N/no (leave empty for: no)." + System.lineSeparator() +
                "main/load:> [1m<==== Main Menu ====>[0m" + System.lineSeparator() +
                "Welcome to ToDo!" + System.lineSeparator() +
                System.lineSeparator() +
                "Commands: " + System.lineSeparator() +
                "  intray" + System.lineSeparator() +
                "  tasks" + System.lineSeparator() +
                "  projects" + System.lineSeparator() +
                "  calendar" + System.lineSeparator() +
                "  load" + System.lineSeparator() +
                "  save" + System.lineSeparator() +
                "  quit" + System.lineSeparator() +
                "    Quit the program." + System.lineSeparator() +
                "main:> "
        );
    }

}
