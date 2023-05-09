package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.core.Project;
import hwr.oop.group4.todo.core.TodoList;
import hwr.oop.group4.todo.ui.controller.ConsoleController;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProjectUiTest {

    private final String projectsMenuOutput =
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
            "projects:> ";

    private String retrieveResultFrom(OutputStream outputStream) {
        return outputStream.toString();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    private TodoList getExampleTodoList(boolean addExampleProjects) {
        TodoList todoList = new TodoList();

        if (!addExampleProjects) {
            return todoList;
        }

        todoList.addProject(new Project.ProjectBuilder()
                .name("TEst")
                .description("Desc")
                .begin(LocalDateTime.of(2013, 12, 12, 15, 45))
                .end(LocalDateTime.of(2014, 12, 12, 12, 12))
                .build()
        );
        todoList.addProject(new Project.ProjectBuilder()
                .name("proj")
                .description("qwer")
                .begin(LocalDateTime.of(2003, 12, 22, 5, 45))
                .end(LocalDateTime.of(2014, 1, 10, 12, 12))
                .build()
        );

        return todoList;
    }

    @Test
    void canListProjects() {
        InputStream inputStream = createInputStreamForInput("list" + System.lineSeparator() + "back" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new ConsoleController(outputStream, inputStream));
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "|  0 |            TEst |                           Desc |            | 12.12. | 12.12. |" + System.lineSeparator() +
                "|  1 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                projectsMenuOutput +
                "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "|  0 |            TEst |                           Desc |            | 12.12. | 12.12. |" + System.lineSeparator() +
                "|  1 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                "projects:> "
        );
    }

    @Test
    void canCreateNewProject() {
        InputStream inputStream = createInputStreamForInput("new" + System.lineSeparator() +
                "Peter" + System.lineSeparator() +
                "Parker" + System.lineSeparator() +
                "12.12.2023" + System.lineSeparator() +
                "24.12.2023" + System.lineSeparator() +
                System.lineSeparator() +
                "list" + System.lineSeparator() +
                "back" + System.lineSeparator()
        );
        OutputStream outputStream = new ByteArrayOutputStream();

        TodoList todoList = getExampleTodoList(false);

        ProjectUi ui = new ProjectUi(new ConsoleController(outputStream, inputStream));
        ui.menu(todoList);

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                projectsMenuOutput +
                "projects/new/name:> " +
                "projects/new/description:> " +
                "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': " +
                "projects/new/begin:> " +
                "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': " +
                "projects/new/end:> " +
                "projects:> " +
                "projects:> " +
                "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "|  0 |           Peter |                         Parker |            | 12.12. | 24.12. |" + System.lineSeparator() +
                "projects:> "
        );
    }

    @Test
    void canEditProject() {
        InputStream inputStream = createInputStreamForInput(
                "edit -id 0 -name Peter -desc Lustig -addTags tv vt -begin 01.01.2022 -end 10.10.2022" + System.lineSeparator() +
                "list" + System.lineSeparator() +
                "edit -removeTags vt -id 1" + System.lineSeparator() +
                "list" + System.lineSeparator() +
                "back" + System.lineSeparator()
        );
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new ConsoleController(outputStream, inputStream));
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "|  0 |            TEst |                           Desc |            | 12.12. | 12.12. |" + System.lineSeparator() +
                "|  1 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                projectsMenuOutput +
                "projects:> " +
                "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "|  0 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                "|  1 |           Peter |                         Lustig |     tv, vt | 01.01. | 10.10. |" + System.lineSeparator() +
                "projects:> " +
                "projects:> " +
                "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "|  0 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                "|  1 |           Peter |                         Lustig |         tv | 01.01. | 10.10. |" + System.lineSeparator() +
                "projects:> "
        );
    }

    @Test
    void cantEditProjectWithInvalidId() {
        InputStream inputStream = createInputStreamForInput(
                "edit -id -name Peter -desc Lustig -addTag tv -begin 01.01.2022 -end 10.10.2022" + System.lineSeparator() +
                        "back" + System.lineSeparator()
        );
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new ConsoleController(outputStream, inputStream));
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                        "========================================================================================" + System.lineSeparator() +
                        "|  0 |            TEst |                           Desc |            | 12.12. | 12.12. |" + System.lineSeparator() +
                        "|  1 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                        projectsMenuOutput +
                        "ID Argument has no parameter." + System.lineSeparator() +
                        "projects:> "
        );
    }

    @Test
    void canRemoveProject() {
        InputStream inputStream = createInputStreamForInput("remove -id 0" + System.lineSeparator() +
                "y" + System.lineSeparator() +
                "list" + System.lineSeparator() +
                "back" + System.lineSeparator()
        );
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new ConsoleController(outputStream, inputStream));
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "|  0 |            TEst |                           Desc |            | 12.12. | 12.12. |" + System.lineSeparator() +
                "|  1 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                projectsMenuOutput +
                "Do you really want to remove TEst?" + System.lineSeparator() +
                "Answer y/Y/yes or n/N/no (leave empty for: no)." + System.lineSeparator() +
                "projects/remove:> " +
                "projects:> " +
                "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "|  0 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                "projects:> "
        );
    }

    @Test
    void cantRemoveProjectWithInvalidId() {
        InputStream inputStream = createInputStreamForInput("remove -id" + System.lineSeparator() +
                "list" + System.lineSeparator() +
                "back" + System.lineSeparator()
        );
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new ConsoleController(outputStream, inputStream));
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                        "========================================================================================" + System.lineSeparator() +
                        "|  0 |            TEst |                           Desc |            | 12.12. | 12.12. |" + System.lineSeparator() +
                        "|  1 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                        projectsMenuOutput +
                        "ID Argument has no parameter." + System.lineSeparator() +
                        "projects:> " +
                        "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                        "========================================================================================" + System.lineSeparator() +
                        "|  0 |            TEst |                           Desc |            | 12.12. | 12.12. |" + System.lineSeparator() +
                        "|  1 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                        "projects:> "
        );
    }

    @Test
    void canOpenTaskMenu() {
        InputStream inputStream = createInputStreamForInput("tasks" + System.lineSeparator() + "back" + System.lineSeparator());
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new ConsoleController(outputStream, inputStream));
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "| ID | Name            | Description                    | Tags       | Begin  | End    |" + System.lineSeparator() +
                "========================================================================================" + System.lineSeparator() +
                "|  0 |            TEst |                           Desc |            | 12.12. | 12.12. |" + System.lineSeparator() +
                "|  1 |            proj |                           qwer |            | 22.12. | 10.01. |" + System.lineSeparator() +
                projectsMenuOutput +
                "projects:> "
        );
    }

}
