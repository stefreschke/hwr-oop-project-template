package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.Project;
import hwr.oop.group4.todo.Tag;
import hwr.oop.group4.todo.TodoList;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProjectUi {

    private final PrintStream out;
    private final Scanner in;
    private final DialogHelper dialogHelper;
    private TodoList todoList;

    public ProjectUi(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
        dialogHelper = new DialogHelper(out, in);
    }

    public void menu(TodoList todoList) {
        this.todoList = todoList;
        listProjects();

        Map<String, String> options = new LinkedHashMap<>();
        options.put("list",   "List all projects.");
        options.put("new",    "Add a new project.");
        options.put("tasks",  "Open the task menu for a project.");
        options.put("edit",   "Edit the attributes of a project.");
        options.put("remove", "Remove a project.");
        options.put("quit",   "Quit to the previous menu.");

        while (true) {
            String input = dialogHelper.getMenuSelectionFromUser("Projects Menu", "projects> ", options);
            int size = todoList.getProjects().size();
            Integer id;
            switch (input) {
                case "list":
                    listProjects();
                    break;
                case "new":
                    newProject();
                    break;
                case "tasks":
                    // TODO: call upcoming TaskUi
                    break;
                case "edit":
                    break;
                case "remove":
                    id = dialogHelper.getValidIdFromUser("Enter the ID of the project you wish to remove: ", size);
                    if (id == null) {
                        out.println("There are no projects to remove.");
                    } else {
                        removeProject(id);
                    }
                    break;
                case "quit":
                    return;
                default:
                    break;
            }
        }
    }

    private void listProjects() {
        String format = "%2d | %15.15s | %30.30s | %10.10s | %6.6s | %6.6s%n";
        final List<Project> projects = todoList.getProjects();

        final int idColumnLength = Math.max((int) Math.ceil(Math.log10(projects.size()) - 2), 0);

        out.println("ID" + (" ").repeat(idColumnLength) + " | Name            | Description                    | Tags       | Begin  | End   ");
        out.println("==" + ("=").repeat(idColumnLength) + "==================================================================================");
        for (int i = 0; i < projects.size(); i++) {
            final Project project = projects.get(i);
            out.printf(format, i, project.getName(), project.getDescription(),
                    concatTagsToString(project.getTags()),
                    project.getBegin().format(DateTimeFormatter.ofPattern("dd.MM.")),
                    project.getEnd().format(DateTimeFormatter.ofPattern("dd.MM.")));
        }
    }

    private String concatTagsToString(Collection<Tag> tags) {
        StringBuilder stringBuilder = new StringBuilder();
        tags.stream().forEach(tag -> stringBuilder.append(tag.getName()));
        return stringBuilder.toString();
    }

    private void newProject() {
        out.print("Enter a name:        ");
        String name = in.nextLine();
        out.print("Enter a description: ");
        String desc = in.nextLine();
        LocalDateTime begin = dialogHelper.getLocalDateTimeFromUser("When should the project begin?", true);
        LocalDateTime end = dialogHelper.getLocalDateTimeFromUser("When should the project end?", false);

        Project project = new Project.ProjectBuilder()
                .name(name)
                .description(desc)
                .begin(begin)
                .end(end)
                .build();

        todoList.addProject(project);
    }

    private void removeProject(int id) {
        String projectName = todoList.getProjects().get(id).getName();
        String confirmation = "Do you really want to remove " + projectName + "?";
        if (dialogHelper.getYesNoFromUser(confirmation, false)) {
            // TODO: delete a Project
        }
    }

}