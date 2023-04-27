package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.core.Project;
import hwr.oop.group4.todo.core.Tag;
import hwr.oop.group4.todo.core.TodoList;
import hwr.oop.group4.todo.ui.controller.ConsoleController;
import hwr.oop.group4.todo.ui.controller.command.Command;
import hwr.oop.group4.todo.ui.controller.command.CommandArgument;
import hwr.oop.group4.todo.ui.controller.menu.Entry;
import hwr.oop.group4.todo.ui.controller.menu.EntryArgument;
import hwr.oop.group4.todo.ui.controller.menu.Menu;
import hwr.oop.group4.todo.ui.controller.tables.ColumnConfig;
import hwr.oop.group4.todo.ui.controller.tables.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProjectUi {

    private final ConsoleController consoleController;
    private TodoList todoList;

    public ProjectUi(ConsoleController consoleController) {
        this.consoleController = consoleController;
    }

    public void menu(TodoList todoList) {
        this.todoList = todoList;
        listProjects(null);

        Menu menu = new Menu("Project Menu", "Manage your Projects!", List.of(
                new Entry("list", "List all projects."),
                new Entry("new",    "Add a new project."),
                new Entry("tasks",  "Open the task menu for a project.", List.of(
                        new EntryArgument("id <id>", "ID of the project."))),
                new Entry("edit",   "Edit the attributes of a project.", List.of(
                        new EntryArgument("id <id>", "ID of the project to be edited."),
                        new EntryArgument("name <name>", "Change the name of the project."),
                        new EntryArgument("desc <desc>", "Change the description of the project."),
                        new EntryArgument("begin", "Change the beginning of the project."),
                        new EntryArgument("end", "Change the end of the project"),
                        new EntryArgument("addTag <tag>", "Add a new tag."),
                        new EntryArgument("removeTag <tag>", "Remove a tag."))),
                new Entry("remove", "Remove a project.", List.of(
                        new EntryArgument("id <id>", "ID of the project to be removed."))),
                new Entry("back",   "Returns to the previous menu.")
        ));
        consoleController.output(menu.toString());

        AtomicBoolean shouldReturn = new AtomicBoolean(false);
        while (!shouldReturn.get()) {
            consoleController.inputOptions(List.of("projects"), List.of(
                    new Command("list",   this::listProjects),
                    new Command("new",    this::newProject),
                    new Command("tasks",  args -> {}),
                    new Command("edit",   this::editProject),
                    new Command("remove", this::removeProject),
                    new Command("back",   args -> shouldReturn.set(true))
            ), new Command("wrongInput", args -> {}));
        }
    }

    private void listProjects(Collection<CommandArgument<?>> args) {
        final List<Project> projects = todoList.getProjects();
        final int idColumnLength = Math.max((int) Math.ceil(Math.log10(projects.size()) - 2), 2);
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
        tags.stream().forEach(tag -> stringBuilder.append(tag.name()));
        return stringBuilder.toString();
    }

    private void newProject(Collection<CommandArgument<?>> args) {
        String name = consoleController.input(List.of("projects", "new", "name")).orElseThrow();
        String desc = consoleController.input(List.of("projects", "new", "description")).orElseThrow();
        LocalDateTime begin = consoleController.inputDate(List.of("projects", "new", "begin"));
        LocalDateTime end = consoleController.inputDate(List.of("projects", "new", "end"));

        Project project = new Project.ProjectBuilder()
                .name(name)
                .description(desc)
                .begin(begin)
                .end(end)
                .build();

        todoList.addProject(project);
    }

    private void removeProject(Collection<CommandArgument<?>> args) {
        Integer id = getId(args);
        if (id == null) {
            return;
        }

        String projectName = todoList.getProjects().get(id).getName();
        String confirmation = "Do you really want to remove " + projectName + "?";
        if (consoleController.inputBool(List.of("projects", "remove"), confirmation, false)) {
            todoList.removeProject(todoList.getProjects().get(id));
        }
    }

    private Integer getId(Collection<CommandArgument<?>> args) {
        Optional<CommandArgument<?>> id_arg = args.stream()
                .filter(arg -> arg.getName().equals("id"))
                .findAny();

        if (id_arg.isEmpty()) {
            consoleController.outputLine("Error: ID Argument required.");
            return null;
        }

        if (! (id_arg.get().getValue() instanceof String)) {
            consoleController.outputLine("Error: ID Argument requires parameter.");
            return null;
        }

        int id;
        try {
            id = Integer.parseInt((String) id_arg.get().getValue());
        } catch (NumberFormatException e) {
            consoleController.outputLine("Error: ID is not a valid number.");
            return null;
        }

        if (id < 0 || id >= todoList.getProjects().size()) {
            consoleController.outputLine("Error: ID is invalid.");
            return null;
        }

        return id;
    }

    private void editProject(Collection<CommandArgument<?>> args) {
        final Integer id = getId(args);
        if (id == null) {
            return;
        }
        Project project = todoList.getProjects().get(id);

        final String name = getStringParameter(args, "name");
        if (name != null) {
            //project.name = parameter;
        }

        final String desc = getStringParameter(args, "desc");
        if (desc != null) {
            //project.desc = parameter;
        }

        final Optional<CommandArgument<?>> begin = args.stream()
                .filter(argument -> argument.getName().equals("begin"))
                .findAny();
        if (begin.isPresent()) {
            consoleController.inputDate(List.of("projects", "edit", "begin"));
        }

        final Optional<CommandArgument<?>> end = args.stream()
                .filter(argument -> argument.getName().equals("end"))
                .findAny();
        if (end.isPresent()) {
            consoleController.inputDate(List.of("projects", "edit", "end"));
        }

        final String addTag = getStringParameter(args, "addTag");
        if (addTag != null) {
            //project.addTAg = parameter;
        }

        final String removeTag = getStringParameter(args, "removeTag");
        if (removeTag != null) {
            //project.removeTAg = parameter;
        }
    }

    private String getStringParameter(Collection<CommandArgument<?>> args, String name) {
        Optional<CommandArgument<?>> arg = args.stream()
                .filter(argument -> argument.getName().equals(name))
                .findAny();

        if (arg.isEmpty()) {
            return null;
        }

        if (! (arg.get().getValue() instanceof String)) {
            consoleController.outputLine("Error: " + name + " Argument requires parameter.");
            return null;
        }

        return (String) arg.get().getValue();
    }

}