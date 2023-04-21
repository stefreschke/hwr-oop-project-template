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
}
