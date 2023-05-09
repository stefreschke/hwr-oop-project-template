package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.core.Idea;
import hwr.oop.group4.todo.core.TodoList;
import hwr.oop.group4.todo.ui.controller.ConsoleController;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IntrayUiTest {

    private final String intrayMenuOutput =
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
            "intray:> ";

    private String retrieveResultFrom(OutputStream outputStream) {
        return outputStream.toString();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    private TodoList getExampleTodoList(boolean addExampleIdeas) {
        TodoList todoList = new TodoList();

        if (!addExampleIdeas) {
            return todoList;
        }

        todoList.addIdea(new Idea("Parker"));
        todoList.addIdea(new Idea("Peter", "Lustig"));
        return todoList;
    }

    @Test
    void canListIdeas() {
        InputStream inputStream = createInputStreamForInput(
                "tsil" + System.lineSeparator() +
                "list" + System.lineSeparator() +
                "back" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();
        IntrayUi ui = new IntrayUi(new ConsoleController(outputStream, inputStream));

        ui.menu(getExampleTodoList(true));
        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                "==================================================================================" + System.lineSeparator() +
                "|  0 |               Parker |                                                    |" + System.lineSeparator() +
                "|  1 |                Peter |                                             Lustig |" + System.lineSeparator() +
                intrayMenuOutput +
                "intray:> " +
                "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                "==================================================================================" + System.lineSeparator() +
                "|  0 |               Parker |                                                    |" + System.lineSeparator() +
                "|  1 |                Peter |                                             Lustig |" + System.lineSeparator() +
                "intray:> "
        );
    }

    @Test
    void canAddIdea() {
        InputStream inputStream = createInputStreamForInput(
                "new" + System.lineSeparator() +
                "abc" + System.lineSeparator() +
                "def" + System.lineSeparator() +
                "list" + System.lineSeparator() +
                "back" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();
        IntrayUi ui = new IntrayUi(new ConsoleController(outputStream, inputStream));

        ui.menu(getExampleTodoList(false));
        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                        "==================================================================================" + System.lineSeparator() +
                        intrayMenuOutput +
                        "intray/new/name:> " +
                        "intray/new/description:> " +
                        "intray:> " +
                        "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                        "==================================================================================" + System.lineSeparator() +
                        "|  0 |                  abc |                                                def |" + System.lineSeparator() +
                        "intray:> "
        );
    }

    @Test
    void canAddIdeaWithoutDescription() {
        InputStream inputStream = createInputStreamForInput(
                "new" + System.lineSeparator() +
                "abc" + System.lineSeparator() +
                System.lineSeparator() +
                "list" + System.lineSeparator() +
                "back" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();
        IntrayUi ui = new IntrayUi(new ConsoleController(outputStream, inputStream));

        ui.menu(getExampleTodoList(false));
        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                        "==================================================================================" + System.lineSeparator() +
                        intrayMenuOutput +
                        "intray/new/name:> " +
                        "intray/new/description:> " +
                        "intray:> " +
                        "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                        "==================================================================================" + System.lineSeparator() +
                        "|  0 |                  abc |                                                    |" + System.lineSeparator() +
                        "intray:> "
        );
    }

    @Test
    void canRemoveIdea() {
        InputStream inputStream = createInputStreamForInput(
                "remove -id 0" + System.lineSeparator() +
                "y" + System.lineSeparator() +
                "list" + System.lineSeparator() +
                "back" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();
        IntrayUi ui = new IntrayUi(new ConsoleController(outputStream, inputStream));

        ui.menu(getExampleTodoList(true));
        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                        "==================================================================================" + System.lineSeparator() +
                        "|  0 |               Parker |                                                    |" + System.lineSeparator() +
                        "|  1 |                Peter |                                             Lustig |" + System.lineSeparator() +
                        intrayMenuOutput +
                        "Do you really want to remove Parker?" + System.lineSeparator() +
                        "Answer y/Y/yes or n/N/no (leave empty for: no)." + System.lineSeparator() +
                        "intray/remove:> " +
                        "intray:> " +
                        "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                        "==================================================================================" + System.lineSeparator() +
                        "|  0 |                Peter |                                             Lustig |" + System.lineSeparator() +
                        "intray:> "
        );
    }

    @Test
    void cantRemoveIdeaWithInvalidId() {
        InputStream inputStream = createInputStreamForInput(
                "remove -id 3" + System.lineSeparator() +
                        "list" + System.lineSeparator() +
                        "back" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();
        IntrayUi ui = new IntrayUi(new ConsoleController(outputStream, inputStream));

        ui.menu(getExampleTodoList(true));
        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                        "==================================================================================" + System.lineSeparator() +
                        "|  0 |               Parker |                                                    |" + System.lineSeparator() +
                        "|  1 |                Peter |                                             Lustig |" + System.lineSeparator() +
                        intrayMenuOutput +
                        "ID parameter is invalid." + System.lineSeparator() +
                        "intray:> " +
                        "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                        "==================================================================================" + System.lineSeparator() +
                        "|  0 |               Parker |                                                    |" + System.lineSeparator() +
                        "|  1 |                Peter |                                             Lustig |" + System.lineSeparator() +
                        "intray:> "
        );
    }

    @Test
    void canConvertIdeaToTasks() {
        InputStream inputStream = createInputStreamForInput(
                "task" + System.lineSeparator() +
                "back" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();
        IntrayUi ui = new IntrayUi(new ConsoleController(outputStream, inputStream));

        ui.menu(getExampleTodoList(true));
        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "| ID | Name                 | Description                                        |" + System.lineSeparator() +
                        "==================================================================================" + System.lineSeparator() +
                        "|  0 |               Parker |                                                    |" + System.lineSeparator() +
                        "|  1 |                Peter |                                             Lustig |" + System.lineSeparator() +
                        intrayMenuOutput +
                        "intray:> "
        );
    }

}
