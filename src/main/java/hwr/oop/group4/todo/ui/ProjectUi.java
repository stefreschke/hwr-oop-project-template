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
            switch (input) {
                case "list":
                    listProjects();
                    break;
                case "new":
                    break;
                case "tasks":
                    break;
                case "edit":
                    break;
                case "remove":
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
}