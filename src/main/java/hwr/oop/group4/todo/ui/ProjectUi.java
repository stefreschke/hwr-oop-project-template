package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.Project;
import hwr.oop.group4.todo.Tag;
import hwr.oop.group4.todo.TodoList;
import hwr.oop.group4.todo.ui.controller.ConsoleController;
import hwr.oop.group4.todo.ui.controller.command.Argument;
import hwr.oop.group4.todo.ui.controller.command.Command;
import hwr.oop.group4.todo.ui.controller.menu.Entry;
import hwr.oop.group4.todo.ui.controller.menu.EntryArgument;
import hwr.oop.group4.todo.ui.controller.menu.Menu;
import hwr.oop.group4.todo.ui.controller.tables.ColumnConfig;
import hwr.oop.group4.todo.ui.controller.tables.Table;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProjectUi {

    private final ConsoleController consoleController;
    private TodoList todoList;

    public ProjectUi(PrintStream out, InputStream in) {
        consoleController = new ConsoleController(out, in);
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

        Menu menu = new Menu("Project Menu", "Manage your Projects!", List.of(
                new Entry("list", "List all projects."),
                new Entry("new",    "Add a new project."),
                new Entry("tasks",  "Open the task menu for a project.", List.of(
                        new EntryArgument("id", "ID of the project."))),
                new Entry("edit",   "Edit the attributes of a project.", List.of(
                        new EntryArgument("id", "ID of the project to be edited."),
                        new EntryArgument("name", "Change the name of the project."),
                        new EntryArgument("desc", "Change the description of the project."),
                        new EntryArgument("begin", "Change the beginning of the project."),
                        new EntryArgument("end", "Change the end of the project"),
                        new EntryArgument("addTag", "Add a new tag."),
                        new EntryArgument("removeTag", "Remove a tag."))),
                new Entry("remove", "Remove a project.", List.of(
                        new EntryArgument("id", "ID of the project to be removed."))),
                new Entry("back",   "Returns to the previous menu.")
        ));

        AtomicBoolean shouldReturn = new AtomicBoolean(false);
        while (!shouldReturn.get()) {
            consoleController.output(menu.toString());

            consoleController.inputOptions(List.of("projects"), List.of(
                    new Command("list", this::listProjects),
                    new Command("new", ),
                    new Command("tasks", ),
                    new Command("edit", ),
                    new Command("remove", ),
                    new Command("back", args -> shouldReturn.set(true))
            ), new Command("wrongInput", args -> {}));
        }
    }

    private void listProjects(Collection<Argument<?>> args) {
        final List<Project> projects = todoList.getProjects();
        final int idColumnLength = Math.max((int) Math.ceil(Math.log10(projects.size()) - 2), 0);
        final Table projectTable = new Table(List.of(
                new ColumnConfig("ID", idColumnLength),
                new ColumnConfig("Name", 15),
                new ColumnConfig("Description", 30),
                new ColumnConfig("Tags", 10),
                new ColumnConfig("Begin", 6),
                new ColumnConfig("End", 6)
        ));

        for (int i = 0; i < projects.size(); i++) {
            final Project project = projects.get(i);
            projectTable.addRow(
                    String.valueOf(i),
                    project.getName(),
                    project.getDescription(),
                    concatTagsToString(project.getTags()),
                    project.getBegin().format(DateTimeFormatter.ofPattern("dd.MM.")),
                    project.getEnd().format(DateTimeFormatter.ofPattern("dd.MM."))
            );
        }

        consoleController.output(projectTable.toString());
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
        int newId = todoList.getProjects().indexOf(project);

        String question = "Do you want to open the edit menu to further customize the new project?";
        if (dialogHelper.getYesNoFromUser(question, false)) {
            editProject(newId);
        }
    }

    private void removeProject(int id) {
        String projectName = todoList.getProjects().get(id).getName();
        String confirmation = "Do you really want to remove " + projectName + "?";
        if (dialogHelper.getYesNoFromUser(confirmation, false)) {
            // TODO: delete a Project
        }
    }

    private void editProject(int id) {
        Project project = todoList.getProjects().get(id);

        Map<String, String> options = new LinkedHashMap<>();
        options.put("name",  "");
        options.put("desc",  "");
        options.put("tags",  "");
        options.put("begin", "");
        options.put("end",   "");
        options.put("quit",  "Quit to the previous menu.");

        while (true) {
            out.println("Name:        " + project.getName());
            out.println("Description: " + project.getDescription());
            out.println("Tags:        " + concatTagsToString(project.getTags()));
            out.println("Begin:       " + project.getBegin().format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm")));
            out.println("End:         " + project.getEnd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm")));

            String input = dialogHelper.getMenuSelectionFromUser("Edit a project.", "projects> edit> ", options);

            // TODO: edit the project
            switch (input) {
                case "name":
                    break;
                case "desc":
                    break;
                case "tags":
                    break;
                case "begin":
                    break;
                case "end":
                    break;
                case "quit":
                    return;
                default:
                    break;
            }
        }
    }

}