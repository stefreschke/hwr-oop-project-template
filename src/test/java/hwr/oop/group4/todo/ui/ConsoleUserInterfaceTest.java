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
    void canCreateNewTodoList() {
        InputStream inputStream = createInputStreamForInput("no\nload\nN\nquit\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(outputStream, inputStream);
        ui.mainMenu();

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
        "main> Do you want to load from a file? (Otherwise create an empty todo list)\n" +
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