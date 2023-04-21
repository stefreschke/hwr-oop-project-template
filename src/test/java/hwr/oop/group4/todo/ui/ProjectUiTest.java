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
        Scanner inputStream = new Scanner(createInputStreamForInput("list\nquit\n"));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "ID | Name            | Description                    | Tags       | Begin  | End   \n" +
                "====================================================================================\n" +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12.\n" +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01.\n" +
                "Projects Menu\n" +
                "                          list - List all projects.\n" +
                "                           new - Add a new project.\n" +
                "                         tasks - Open the task menu for a project.\n" +
                "                          edit - Edit the attributes of a project.\n" +
                "                        remove - Remove a project.\n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> ID | Name            | Description                    | Tags       | Begin  | End   \n" +
                "====================================================================================\n" +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12.\n" +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01.\n" +
                "Projects Menu\n" +
                "                          list - List all projects.\n" +
                "                           new - Add a new project.\n" +
                "                         tasks - Open the task menu for a project.\n" +
                "                          edit - Edit the attributes of a project.\n" +
                "                        remove - Remove a project.\n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> ");
    }

    @Test
    void canCreateNewProject() {
        Scanner inputStream = new Scanner(createInputStreamForInput("new\nPeter\nParker\n12.12.2023\n24.12.2023\nno\nlist\nquit\n"));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
            "ID | Name            | Description                    | Tags       | Begin  | End   \n" +
                    "====================================================================================\n" +
                    " 0 |            TEst |                           Desc |            | 12.12. | 12.12.\n" +
                    " 1 |            proj |                           qwer |            | 22.12. | 10.01.\n" +
                    "Projects Menu\n" +
                    "                          list - List all projects.\n" +
                    "                           new - Add a new project.\n" +
                    "                         tasks - Open the task menu for a project.\n" +
                    "                          edit - Edit the attributes of a project.\n" +
                    "                        remove - Remove a project.\n" +
                    "                          quit - Quit to the previous menu.\n" +
                    "projects> Enter a name:        Enter a description: When should the project begin?\n" +
                    "The current date/time will be used if you leave this empty.\n" +
                    "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': When should the project end?\n" +
                    "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': Do you want to open the edit menu to further customize the new project?\n" +
                    "Answer y/Y/yes or n/N/no (leave empty for: no): Projects Menu\n" +
                    "                          list - List all projects.\n" +
                    "                           new - Add a new project.\n" +
                    "                         tasks - Open the task menu for a project.\n" +
                    "                          edit - Edit the attributes of a project.\n" +
                    "                        remove - Remove a project.\n" +
                    "                          quit - Quit to the previous menu.\n" +
                    "projects> ID | Name            | Description                    | Tags       | Begin  | End   \n" +
                    "====================================================================================\n" +
                    " 0 |            TEst |                           Desc |            | 12.12. | 12.12.\n" +
                    " 1 |            proj |                           qwer |            | 22.12. | 10.01.\n" +
                    " 2 |           Peter |                         Parker |            | 12.12. | 24.12.\n" +
                    "Projects Menu\n" +
                    "                          list - List all projects.\n" +
                    "                           new - Add a new project.\n" +
                    "                         tasks - Open the task menu for a project.\n" +
                    "                          edit - Edit the attributes of a project.\n" +
                    "                        remove - Remove a project.\n" +
                    "                          quit - Quit to the previous menu.\n" +
                    "projects> ");
    }

    @Test
    void canCreateNewProjectAndEdit() {
        Scanner inputStream = new Scanner(createInputStreamForInput("new\nPeter\nParker\n12.12.2023\n24.12.2023\nyes\nquit\nlist\nquit\n"));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "ID | Name            | Description                    | Tags       | Begin  | End   \n" +
                "====================================================================================\n" +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12.\n" +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01.\n" +
                "Projects Menu\n" +
                "                          list - List all projects.\n" +
                "                           new - Add a new project.\n" +
                "                         tasks - Open the task menu for a project.\n" +
                "                          edit - Edit the attributes of a project.\n" +
                "                        remove - Remove a project.\n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> Enter a name:        Enter a description: When should the project begin?\n" +
                "The current date/time will be used if you leave this empty.\n" +
                "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': When should the project end?\n" +
                "Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': Do you want to open the edit menu to further customize the new project?\n" +
                "Answer y/Y/yes or n/N/no (leave empty for: no): Name:        Peter\n" +
                "Description: Parker\n" +
                "Tags:        \n" +
                "Begin:       12.12.2023 - 00:00\n" +
                "End:         24.12.2023 - 00:00\n" +
                "Edit a project.\n" +
                "                          name - \n" +
                "                          desc - \n" +
                "                          tags - \n" +
                "                         begin - \n" +
                "                           end - \n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> edit> Projects Menu\n" +
                "                          list - List all projects.\n" +
                "                           new - Add a new project.\n" +
                "                         tasks - Open the task menu for a project.\n" +
                "                          edit - Edit the attributes of a project.\n" +
                "                        remove - Remove a project.\n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> ID | Name            | Description                    | Tags       | Begin  | End   \n" +
                "====================================================================================\n" +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12.\n" +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01.\n" +
                " 2 |           Peter |                         Parker |            | 12.12. | 24.12.\n" +
                "Projects Menu\n" +
                "                          list - List all projects.\n" +
                "                           new - Add a new project.\n" +
                "                         tasks - Open the task menu for a project.\n" +
                "                          edit - Edit the attributes of a project.\n" +
                "                        remove - Remove a project.\n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> ");
    }

    @Test
    void cannotEditEmptyProjectList() {
        Scanner inputStream = new Scanner(createInputStreamForInput("edit\nquit\n"));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(false));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
        "ID | Name            | Description                    | Tags       | Begin  | End   \n" +
                "====================================================================================\n" +
                "Projects Menu\n" +
                "                          list - List all projects.\n" +
                "                           new - Add a new project.\n" +
                "                         tasks - Open the task menu for a project.\n" +
                "                          edit - Edit the attributes of a project.\n" +
                "                        remove - Remove a project.\n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> There are no projects to edit.\n" +
                "Projects Menu\n" +
                "                          list - List all projects.\n" +
                "                           new - Add a new project.\n" +
                "                         tasks - Open the task menu for a project.\n" +
                "                          edit - Edit the attributes of a project.\n" +
                "                        remove - Remove a project.\n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> ");
    }

    @Test
    void canEditProject() {
        Scanner inputStream = new Scanner(createInputStreamForInput("edit\n1\nname\ndesc\ntags\nbegin\nend\nquit\nquit\n"));
        OutputStream outputStream = new ByteArrayOutputStream();

        ProjectUi ui = new ProjectUi(new PrintStream(outputStream), inputStream);
        ui.menu(getExampleTodoList(true));

        String output = retrieveResultFrom(outputStream);

        assertThat(output).isEqualTo(
                "ID | Name            | Description                    | Tags       | Begin  | End   \n" +
                "====================================================================================\n" +
                " 0 |            TEst |                           Desc |            | 12.12. | 12.12.\n" +
                " 1 |            proj |                           qwer |            | 22.12. | 10.01.\n" +
                "Projects Menu\n" +
                "                          list - List all projects.\n" +
                "                           new - Add a new project.\n" +
                "                         tasks - Open the task menu for a project.\n" +
                "                          edit - Edit the attributes of a project.\n" +
                "                        remove - Remove a project.\n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> Enter the ID of the project you wish to edit: Name:        proj\n" +
                "Description: qwer\n" +
                "Tags:        \n" +
                "Begin:       22.12.2003 - 05:45\n" +
                "End:         10.01.2014 - 12:12\n" +
                "Edit a project.\n" +
                "                          name - \n" +
                "                          desc - \n" +
                "                          tags - \n" +
                "                         begin - \n" +
                "                           end - \n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> edit> Name:        proj\n" +
                "Description: qwer\n" +
                "Tags:        \n" +
                "Begin:       22.12.2003 - 05:45\n" +
                "End:         10.01.2014 - 12:12\n" +
                "Edit a project.\n" +
                "                          name - \n" +
                "                          desc - \n" +
                "                          tags - \n" +
                "                         begin - \n" +
                "                           end - \n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> edit> Name:        proj\n" +
                "Description: qwer\n" +
                "Tags:        \n" +
                "Begin:       22.12.2003 - 05:45\n" +
                "End:         10.01.2014 - 12:12\n" +
                "Edit a project.\n" +
                "                          name - \n" +
                "                          desc - \n" +
                "                          tags - \n" +
                "                         begin - \n" +
                "                           end - \n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> edit> Name:        proj\n" +
                "Description: qwer\n" +
                "Tags:        \n" +
                "Begin:       22.12.2003 - 05:45\n" +
                "End:         10.01.2014 - 12:12\n" +
                "Edit a project.\n" +
                "                          name - \n" +
                "                          desc - \n" +
                "                          tags - \n" +
                "                         begin - \n" +
                "                           end - \n" +
                "                          quit - Quit to the previous menu.\n" +
        "projects> edit> Name:        proj\n" +
        "Description: qwer\n" +
        "Tags:        \n" +
        "Begin:       22.12.2003 - 05:45\n" +
        "End:         10.01.2014 - 12:12\n" +
        "Edit a project.\n" +
                "                          name - \n" +
                "                          desc - \n" +
                "                          tags - \n" +
                "                         begin - \n" +
                "                           end - \n" +
                "                          quit - Quit to the previous menu.\n" +
        "projects> edit> Name:        proj\n" +
        "Description: qwer\n" +
        "Tags:        \n" +
        "Begin:       22.12.2003 - 05:45\n" +
        "End:         10.01.2014 - 12:12\n" +
        "Edit a project.\n" +
                "                          name - \n" +
                "                          desc - \n" +
                "                          tags - \n" +
                "                         begin - \n" +
                "                           end - \n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> edit> Projects Menu\n" +
                "                          list - List all projects.\n" +
                "                           new - Add a new project.\n" +
                "                         tasks - Open the task menu for a project.\n" +
                "                          edit - Edit the attributes of a project.\n" +
                "                        remove - Remove a project.\n" +
                "                          quit - Quit to the previous menu.\n" +
                "projects> ");
    }

}