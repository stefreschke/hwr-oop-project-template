package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.ui.controller.ConsoleController;
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
        InputStream inputStream = createInputStreamForInput("no" + System.lineSeparator() + "load" + System.lineSeparator() + "N" + System.lineSeparator() + "quit" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

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
        "main> Do you want to load from a file? (Otherwise create an empty todo list)" + System.lineSeparator() +
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

    @Test
    void canLoadAndSaveTodoListFromFile() {
        InputStream inputStream = createInputStreamForInput("yes" + System.lineSeparator() + "save" + System.lineSeparator() + "load" + System.lineSeparator() + "Y" + System.lineSeparator() + "quit" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

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
                        "main> Main Menu" + System.lineSeparator() +
                        "                        intray - " + System.lineSeparator() +
                        "                         tasks - " + System.lineSeparator() +
                        "                      projects - " + System.lineSeparator() +
                        "                      calendar - " + System.lineSeparator() +
                        "                          load - " + System.lineSeparator() +
                        "                          save - " + System.lineSeparator() +
                        "                          quit - Quit the program." + System.lineSeparator() +
                        "main> Do you want to load from a file? (Otherwise create an empty todo list)" + System.lineSeparator() +
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

    @Test
    void canOpenProjectsMenu() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() + "protsch" + System.lineSeparator() + "projects" + System.lineSeparator() + "quit" + System.lineSeparator() + "quit" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

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
                "main> main> ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                "====================================================================================" + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> Main Menu" + System.lineSeparator() +
                "                        intray - " + System.lineSeparator() +
                "                         tasks - " + System.lineSeparator() +
                "                      projects - " + System.lineSeparator() +
                "                      calendar - " + System.lineSeparator() +
                "                          load - " + System.lineSeparator() +
                "                          save - " + System.lineSeparator() +
                "                          quit - Quit the program." + System.lineSeparator() +
                "main> ");
    }

    @Test
    void canOpenIntrayMenu() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() + "intray" + System.lineSeparator() + "quit" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

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
                        "main> Main Menu" + System.lineSeparator() +
                        "                        intray - " + System.lineSeparator() +
                        "                         tasks - " + System.lineSeparator() +
                        "                      projects - " + System.lineSeparator() +
                        "                      calendar - " + System.lineSeparator() +
                        "                          load - " + System.lineSeparator() +
                        "                          save - " + System.lineSeparator() +
                        "                          quit - Quit the program." + System.lineSeparator() +
                        "main> ");
    }

    @Test
    void canOpenTasksMenu() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() + "tasks" + System.lineSeparator() + "quit" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

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
                        "main> Main Menu" + System.lineSeparator() +
                        "                        intray - " + System.lineSeparator() +
                        "                         tasks - " + System.lineSeparator() +
                        "                      projects - " + System.lineSeparator() +
                        "                      calendar - " + System.lineSeparator() +
                        "                          load - " + System.lineSeparator() +
                        "                          save - " + System.lineSeparator() +
                        "                          quit - Quit the program." + System.lineSeparator() +
                        "main> ");
    }

    @Test
    void canOpenCalendarMenu() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() + "calendar" + System.lineSeparator() + "quit" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

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
                        "main> Main Menu" + System.lineSeparator() +
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
