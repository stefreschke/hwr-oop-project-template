package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.Project;
import hwr.oop.group4.todo.TodoList;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProjectUiTest {

    private String retrieveResultFrom(OutputStream outputStream) {
        return outputStream.toString();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    private TodoList getExampleTodoList(boolean addExampleProjects) {
        TodoList todoList = new TodoList();

        if (addExampleProjects) {
            todoList.addProject(new Project.ProjectBuilder()
                    .name("TEst")
                    .description("Desc")
                    .begin(LocalDateTime.of(2013, 12, 12, 15, 45))
                    .end(LocalDateTime.of(2014, 12, 12, 12, 12))
                    .build());
            todoList.addProject(new Project.ProjectBuilder()
                    .name("proj")
                    .description("qwer")
                    .begin(LocalDateTime.of(2003, 12, 22, 5, 45))
                    .end(LocalDateTime.of(2014, 1, 10, 12, 12))
                    .build());
        }

        return todoList;
    }

    @Test
    void canListProjects() {
        Scanner inputStream = new Scanner(createInputStreamForInput("list" + System.lineSeparator() + "quit" + System.lineSeparator()));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                "====================================================================================" + System.lineSeparator() +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                "====================================================================================" + System.lineSeparator() +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> ");
    }

    @Test
    void canCreateNewProject() {
        Scanner inputStream = new Scanner(createInputStreamForInput("new" + System.lineSeparator() + "Peter" + System.lineSeparator() + "Parker" + System.lineSeparator() + "12.12.2023" + System.lineSeparator() + "24.12.2023" + System.lineSeparator() + "no" + System.lineSeparator() + "list" + System.lineSeparator() + "quit" + System.lineSeparator()));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
            "ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                    "====================================================================================" + System.lineSeparator() +
                    " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                    " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                    "Projects Menu" + System.lineSeparator() +
                    "                          list - List all projects." + System.lineSeparator() +
                    "                           new - Add a new project." + System.lineSeparator() +
                    "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                    "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                    "                        remove - Remove a project." + System.lineSeparator() +
                    "                          quit - Quit to the previous menu." + System.lineSeparator() +
                    "projects> Enter a name:        Enter a description: When should the project begin?" + System.lineSeparator() +
                    "The current date/time will be used if you leave this empty." + System.lineSeparator() +
                    "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': When should the project end?" + System.lineSeparator() +
                    "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': Do you want to open the edit menu to further customize the new project?" + System.lineSeparator() +
                    "Answer y/Y/yes or n/N/no (leave empty for: no): Projects Menu" + System.lineSeparator() +
                    "                          list - List all projects." + System.lineSeparator() +
                    "                           new - Add a new project." + System.lineSeparator() +
                    "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                    "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                    "                        remove - Remove a project." + System.lineSeparator() +
                    "                          quit - Quit to the previous menu." + System.lineSeparator() +
                    "projects> ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                    "====================================================================================" + System.lineSeparator() +
                    " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                    " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                    " 2 |           Peter |                         Parker |            | 12.12. | 24.12." + System.lineSeparator() +
                    "Projects Menu" + System.lineSeparator() +
                    "                          list - List all projects." + System.lineSeparator() +
                    "                           new - Add a new project." + System.lineSeparator() +
                    "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                    "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                    "                        remove - Remove a project." + System.lineSeparator() +
                    "                          quit - Quit to the previous menu." + System.lineSeparator() +
                    "projects> ");
    }

    @Test
    void canCreateNewProjectAndEdit() {
        Scanner inputStream = new Scanner(createInputStreamForInput("new" + System.lineSeparator() + "Peter" + System.lineSeparator() + "Parker" + System.lineSeparator() + "12.12.2023" + System.lineSeparator() + "24.12.2023" + System.lineSeparator() + "yes" + System.lineSeparator() + "quit" + System.lineSeparator() + "list" + System.lineSeparator() + "quit" + System.lineSeparator()));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                "====================================================================================" + System.lineSeparator() +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> Enter a name:        Enter a description: When should the project begin?" + System.lineSeparator() +
                "The current date/time will be used if you leave this empty." + System.lineSeparator() +
                "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': When should the project end?" + System.lineSeparator() +
                "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': Do you want to open the edit menu to further customize the new project?" + System.lineSeparator() +
                "Answer y/Y/yes or n/N/no (leave empty for: no): Name:        Peter" + System.lineSeparator() +
                "Description: Parker" + System.lineSeparator() +
                "Tags:        " + System.lineSeparator() +
                "Begin:       12.12.2023 - 00:00" + System.lineSeparator() +
                "End:         24.12.2023 - 00:00" + System.lineSeparator() +
                "Edit a project." + System.lineSeparator() +
                "                          name - " + System.lineSeparator() +
                "                          desc - " + System.lineSeparator() +
                "                          tags - " + System.lineSeparator() +
                "                         begin - " + System.lineSeparator() +
                "                           end - " + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> edit> Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                "====================================================================================" + System.lineSeparator() +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                " 2 |           Peter |                         Parker |            | 12.12. | 24.12." + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> ");
    }

    @Test
    void cannotEditEmptyProjectList() {
        Scanner inputStream = new Scanner(createInputStreamForInput("edit" + System.lineSeparator() + "quit" + System.lineSeparator()));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(false));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                "====================================================================================" + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> There are no projects to edit." + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> ");
    }

    @Test
    void canEditProject() {
        Scanner inputStream = new Scanner(createInputStreamForInput("edit" + System.lineSeparator() + "1" + System.lineSeparator() + "name" + System.lineSeparator() + "desc" + System.lineSeparator() + "tags" + System.lineSeparator() + "begin" + System.lineSeparator() + "end" + System.lineSeparator() + "quit" + System.lineSeparator() + "quit" + System.lineSeparator()));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                "====================================================================================" + System.lineSeparator() +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> Enter the ID of the project you wish to edit: Name:        proj" + System.lineSeparator() +
                "Description: qwer" + System.lineSeparator() +
                "Tags:        " + System.lineSeparator() +
                "Begin:       22.12.2003 - 05:45" + System.lineSeparator() +
                "End:         10.01.2014 - 12:12" + System.lineSeparator() +
                "Edit a project." + System.lineSeparator() +
                "                          name - " + System.lineSeparator() +
                "                          desc - " + System.lineSeparator() +
                "                          tags - " + System.lineSeparator() +
                "                         begin - " + System.lineSeparator() +
                "                           end - " + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> edit> Name:        proj" + System.lineSeparator() +
                "Description: qwer" + System.lineSeparator() +
                "Tags:        " + System.lineSeparator() +
                "Begin:       22.12.2003 - 05:45" + System.lineSeparator() +
                "End:         10.01.2014 - 12:12" + System.lineSeparator() +
                "Edit a project." + System.lineSeparator() +
                "                          name - " + System.lineSeparator() +
                "                          desc - " + System.lineSeparator() +
                "                          tags - " + System.lineSeparator() +
                "                         begin - " + System.lineSeparator() +
                "                           end - " + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> edit> Name:        proj" + System.lineSeparator() +
                "Description: qwer" + System.lineSeparator() +
                "Tags:        " + System.lineSeparator() +
                "Begin:       22.12.2003 - 05:45" + System.lineSeparator() +
                "End:         10.01.2014 - 12:12" + System.lineSeparator() +
                "Edit a project." + System.lineSeparator() +
                "                          name - " + System.lineSeparator() +
                "                          desc - " + System.lineSeparator() +
                "                          tags - " + System.lineSeparator() +
                "                         begin - " + System.lineSeparator() +
                "                           end - " + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> edit> Name:        proj" + System.lineSeparator() +
                "Description: qwer" + System.lineSeparator() +
                "Tags:        " + System.lineSeparator() +
                "Begin:       22.12.2003 - 05:45" + System.lineSeparator() +
                "End:         10.01.2014 - 12:12" + System.lineSeparator() +
                "Edit a project." + System.lineSeparator() +
                "                          name - " + System.lineSeparator() +
                "                          desc - " + System.lineSeparator() +
                "                          tags - " + System.lineSeparator() +
                "                         begin - " + System.lineSeparator() +
                "                           end - " + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
        "projects> edit> Name:        proj" + System.lineSeparator() +
        "Description: qwer" + System.lineSeparator() +
        "Tags:        " + System.lineSeparator() +
        "Begin:       22.12.2003 - 05:45" + System.lineSeparator() +
        "End:         10.01.2014 - 12:12" + System.lineSeparator() +
        "Edit a project." + System.lineSeparator() +
                "                          name - " + System.lineSeparator() +
                "                          desc - " + System.lineSeparator() +
                "                          tags - " + System.lineSeparator() +
                "                         begin - " + System.lineSeparator() +
                "                           end - " + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
        "projects> edit> Name:        proj" + System.lineSeparator() +
        "Description: qwer" + System.lineSeparator() +
        "Tags:        " + System.lineSeparator() +
        "Begin:       22.12.2003 - 05:45" + System.lineSeparator() +
        "End:         10.01.2014 - 12:12" + System.lineSeparator() +
        "Edit a project." + System.lineSeparator() +
                "                          name - " + System.lineSeparator() +
                "                          desc - " + System.lineSeparator() +
                "                          tags - " + System.lineSeparator() +
                "                         begin - " + System.lineSeparator() +
                "                           end - " + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> edit> Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> ");
    }

    @Test
    void cannotRemoveEmptyProjectList() {
        Scanner inputStream = new Scanner(createInputStreamForInput("remove" + System.lineSeparator() + "quit" + System.lineSeparator()));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(false));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                        "====================================================================================" + System.lineSeparator() +
                        "Projects Menu" + System.lineSeparator() +
                        "                          list - List all projects." + System.lineSeparator() +
                        "                           new - Add a new project." + System.lineSeparator() +
                        "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                        "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                        "                        remove - Remove a project." + System.lineSeparator() +
                        "                          quit - Quit to the previous menu." + System.lineSeparator() +
                        "projects> There are no projects to remove." + System.lineSeparator() +
                        "Projects Menu" + System.lineSeparator() +
                        "                          list - List all projects." + System.lineSeparator() +
                        "                           new - Add a new project." + System.lineSeparator() +
                        "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                        "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                        "                        remove - Remove a project." + System.lineSeparator() +
                        "                          quit - Quit to the previous menu." + System.lineSeparator() +
                        "projects> ");
    }

    @Test
    void canRejectRemovalOfProject() {
        Scanner inputStream = new Scanner(createInputStreamForInput("remove" + System.lineSeparator() + "1" + System.lineSeparator() + System.lineSeparator() + "list" + System.lineSeparator() + "quit" + System.lineSeparator()));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                        "====================================================================================" + System.lineSeparator() +
                        " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                        " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                        "Projects Menu" + System.lineSeparator() +
                        "                          list - List all projects." + System.lineSeparator() +
                        "                           new - Add a new project." + System.lineSeparator() +
                        "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                        "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                        "                        remove - Remove a project." + System.lineSeparator() +
                        "                          quit - Quit to the previous menu." + System.lineSeparator() +
                        "projects> Enter the ID of the project you wish to remove: Do you really want to remove proj?" + System.lineSeparator() +
                        "Answer y/Y/yes or n/N/no (leave empty for: no): Projects Menu" + System.lineSeparator() +
                        "                          list - List all projects." + System.lineSeparator() +
                        "                           new - Add a new project." + System.lineSeparator() +
                        "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                        "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                        "                        remove - Remove a project." + System.lineSeparator() +
                        "                          quit - Quit to the previous menu." + System.lineSeparator() +
                        "projects> ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                        "====================================================================================" + System.lineSeparator() +
                        " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                        " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                        "Projects Menu" + System.lineSeparator() +
                        "                          list - List all projects." + System.lineSeparator() +
                        "                           new - Add a new project." + System.lineSeparator() +
                        "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                        "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                        "                        remove - Remove a project." + System.lineSeparator() +
                        "                          quit - Quit to the previous menu." + System.lineSeparator() +
                        "projects> ");
    }

    @Test
    void canRemoveProject() {
        Scanner inputStream = new Scanner(createInputStreamForInput("remove" + System.lineSeparator() + "1" + System.lineSeparator() + "y" + System.lineSeparator() + "list" + System.lineSeparator() + "quit" + System.lineSeparator()));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                "====================================================================================" + System.lineSeparator() +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> Enter the ID of the project you wish to remove: Do you really want to remove proj?" + System.lineSeparator() +
                "Answer y/Y/yes or n/N/no (leave empty for: no): Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                "====================================================================================" + System.lineSeparator() +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                "Projects Menu" + System.lineSeparator() +
                "                          list - List all projects." + System.lineSeparator() +
                "                           new - Add a new project." + System.lineSeparator() +
                "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                "                        remove - Remove a project." + System.lineSeparator() +
                "                          quit - Quit to the previous menu." + System.lineSeparator() +
                "projects> ");
    }

    @Test
    void canOpenTaskMenu() {
        Scanner inputStream = new Scanner(createInputStreamForInput("tasks" + System.lineSeparator() + "quit" + System.lineSeparator()));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "ID | Name            | Description                    | Tags       | Begin  | End   " + System.lineSeparator() +
                        "====================================================================================" + System.lineSeparator() +
                        " 0 |            TEst |                           Desc |            | 12.12. | 12.12." + System.lineSeparator() +
                        " 1 |            proj |                           qwer |            | 22.12. | 10.01." + System.lineSeparator() +
                        "Projects Menu" + System.lineSeparator() +
                        "                          list - List all projects." + System.lineSeparator() +
                        "                           new - Add a new project." + System.lineSeparator() +
                        "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                        "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                        "                        remove - Remove a project." + System.lineSeparator() +
                        "                          quit - Quit to the previous menu." + System.lineSeparator() +
                        "projects> " +
                        "Projects Menu" + System.lineSeparator() +
                        "                          list - List all projects." + System.lineSeparator() +
                        "                           new - Add a new project." + System.lineSeparator() +
                        "                         tasks - Open the task menu for a project." + System.lineSeparator() +
                        "                          edit - Edit the attributes of a project." + System.lineSeparator() +
                        "                        remove - Remove a project." + System.lineSeparator() +
                        "                          quit - Quit to the previous menu." + System.lineSeparator() +
                        "projects> ");
    }

}
