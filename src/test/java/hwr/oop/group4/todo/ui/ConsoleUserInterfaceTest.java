package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.ui.controller.ConsoleController;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConsoleUserInterfaceTest {

    private final String loadMenuOutput =
            "Do you want to load from a file? (Otherwise create an empty todo list)" + System.lineSeparator() +
            "Answer y/Y/yes or n/N/no (leave empty for: no)." + System.lineSeparator() +
            "main/load:> ";
    private final String mainMenuOutput =
            "[1m<==== Main Menu ====>[0m" + System.lineSeparator() +
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
            "main:> ";

    private String retrieveResultFrom(OutputStream outputStream) {
        return outputStream.toString();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void canCreateNewTodoList() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() + "load" + System.lineSeparator() +
                "n" + System.lineSeparator() +  "quit" + System.lineSeparator() );
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

        String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo(
        loadMenuOutput +
                mainMenuOutput +
                loadMenuOutput +
                mainMenuOutput
        );
    }

    @Test
    void canLoadAndSaveTodoListFromFile() {
        InputStream inputStream = createInputStreamForInput("yes" + System.lineSeparator() + "save" + System.lineSeparator() +
                "load" + System.lineSeparator() + "Y" + System.lineSeparator() + "quit" + System.lineSeparator()
        );
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        loadMenuOutput +
                mainMenuOutput +
                mainMenuOutput +
                loadMenuOutput +
                mainMenuOutput
        );
    }

    @Test
    void canOpenProjectsMenu() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() + "protsch" + System.lineSeparator() +
                "projects" + System.lineSeparator() + "back" + System.lineSeparator() + "quit" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        loadMenuOutput +
                mainMenuOutput +
                mainMenuOutput +
                "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "[1m<==== Project Menu ====>[0m" + System.lineSeparator() +
                "Manage your Projects!" + System.lineSeparator() +
                System.lineSeparator() +
                "Commands: " + System.lineSeparator() +
                "  list" + System.lineSeparator() +
                "    List all projects." + System.lineSeparator() +
                "  new" + System.lineSeparator() +
                "    Add a new project." + System.lineSeparator() +
                "  tasks" + System.lineSeparator() +
                "    Open the task menu for a project." + System.lineSeparator() +
                "    -id <id>" + System.lineSeparator() +
                "      ID of the project." + System.lineSeparator() +
                "  edit" + System.lineSeparator() +
                "    Edit the attributes of a project." + System.lineSeparator() +
                "    -id <id>" + System.lineSeparator() +
                "      ID of the project to be edited." + System.lineSeparator() +
                "    -name <name>" + System.lineSeparator() +
                "      Change the name of the project." + System.lineSeparator() +
                "    -desc <desc>" + System.lineSeparator() +
                "      Change the description of the project." + System.lineSeparator() +
                "    -begin" + System.lineSeparator() +
                "      Change the beginning of the project." + System.lineSeparator() +
                "    -end" + System.lineSeparator() +
                "      Change the end of the project" + System.lineSeparator() +
                "    -addTags <tag> [<tag> ...]" + System.lineSeparator() +
                "      Add a new tag." + System.lineSeparator() +
                "    -removeTags <tag> [<tag> ...]" + System.lineSeparator() +
                "      Remove a tag." + System.lineSeparator() +
                "  remove" + System.lineSeparator() +
                "    Remove a project." + System.lineSeparator() +
                "    -id <id>" + System.lineSeparator() +
                "      ID of the project to be removed." + System.lineSeparator() +
                "  back" + System.lineSeparator() +
                "    Returns to the previous menu." + System.lineSeparator() +
                "projects:> " +
                mainMenuOutput
        );
    }

    @Test
    void canOpenIntrayMenu() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() +
                "intray" + System.lineSeparator() +
                "back" + System.lineSeparator() +
                "quit" + System.lineSeparator()
        );
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        loadMenuOutput +
                mainMenuOutput +
                "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                "==================================================================================" + System.lineSeparator() +
                "[1m<==== Intray Menu ====>[0m" + System.lineSeparator() +
                "Manage your fleeting thoughts!" + System.lineSeparator() +
                System.lineSeparator() +
                "Commands: " + System.lineSeparator() +
                "  list" + System.lineSeparator() +
                "    List all ideas." + System.lineSeparator() +
                "  new" + System.lineSeparator() +
                "    Create a new idea." + System.lineSeparator() +
                "  remove" + System.lineSeparator() +
                "    Remove an idea" + System.lineSeparator() +
                "    -id <id>" + System.lineSeparator() +
                "      ID of the idea to be removed." + System.lineSeparator() +
                "  task" + System.lineSeparator() +
                "    Create a task from an idea" + System.lineSeparator() +
                "    -id <id>" + System.lineSeparator() +
                "      ID of the idea to be used." + System.lineSeparator() +
                "  back" + System.lineSeparator() +
                "    Return to the previous menu." + System.lineSeparator() +
                "intray:> " +
                mainMenuOutput
        );
    }

    @Test
    void canOpenTasksMenu() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() + "tasks" + System.lineSeparator() +
                "quit" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        loadMenuOutput +
                mainMenuOutput +
                mainMenuOutput
        );
    }

    @Test
    void canOpenCalendarMenu() {
        InputStream inputStream = createInputStreamForInput(System.lineSeparator() + "calendar" + System.lineSeparator() +
                "quit" + System.lineSeparator()
        );
        OutputStream outputStream = new ByteArrayOutputStream();

        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(outputStream, inputStream));
        ui.mainMenu();

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        loadMenuOutput +
                mainMenuOutput +
                mainMenuOutput
        );
    }

}
