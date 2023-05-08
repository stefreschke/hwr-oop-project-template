package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.core.Project;
import hwr.oop.group4.todo.core.Tag;
import hwr.oop.group4.todo.core.Task;
import hwr.oop.group4.todo.core.TodoList;
import hwr.oop.group4.todo.ui.controller.ConsoleController;
import hwr.oop.group4.todo.ui.controller.ConsoleHelper;
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
    private final ConsoleHelper consoleHelper;
    private TodoList todoList;

    public ProjectUi(ConsoleController consoleController) {
        this.consoleController = consoleController;
        this.consoleHelper = new ConsoleHelper(this.consoleController);
    }

    public void menu(TodoList todoList) {
        this.todoList = todoList;
        listProjects(null);

        Menu menu = new Menu("Project Menu", "Manage your Projects!", List.of(
                new Entry("list", "List all projects."),
                new Entry("new",    "Add a new project."),
                new Entry("tasks",  "Open the task menu for a project.", List.of(
                        new EntryArgument("id <id>", "ID of the project.")
                )),
                new Entry("edit",   "Edit the attributes of a project.", List.of(
                        new EntryArgument("id <id>", "ID of the project to be edited."),
                        new EntryArgument("name <name>", "Change the name of the project."),
                        new EntryArgument("desc <desc>", "Change the description of the project."),
                        new EntryArgument("begin", "Change the beginning of the project."),
                        new EntryArgument("end", "Change the end of the project"),
                        new EntryArgument("addTag <tag>", "Add a new tag."),
                        new EntryArgument("removeTag <tag>", "Remove a tag.")
                )),
                new Entry("remove", "Remove a project.", List.of(
                        new EntryArgument("id <id>", "ID of the project to be removed.")
                )),
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

    private void listProjects(Collection<CommandArgument<String>> args) {
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
                    consoleHelper.concatTagsToString(project.getTags()),
                    project.getBegin().format(DateTimeFormatter.ofPattern("dd.MM.")),
                    project.getEnd().format(DateTimeFormatter.ofPattern("dd.MM."))
            );
        }

        consoleController.output(projectTable.toString());
    }

    private void newProject(Collection<CommandArgument<String>> args) {
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

    private void removeProject(Collection<CommandArgument<String>> args) {
        Optional<Integer> id = consoleHelper.getId(args, todoList.getProjects().size());
        if (id.isEmpty()) {
            return;
        }

        String projectName = todoList.getProjects().get(id.get()).getName();
        String confirmation = "Do you really want to remove " + projectName + "?";
        if (consoleController.inputBool(List.of("projects", "remove"), confirmation, false)) {
            todoList.removeProject(todoList.getProjects().get(id.get()));
        }
    }

    private void editProject(Collection<CommandArgument<String>> args) {
        final Optional<Integer> id = consoleHelper.getId(args, todoList.getProjects().size());
        if (id.isEmpty()) {
            return;
        }
        Project project = todoList.getProjects().get(id.get());
        todoList.removeProject(project);
        Project.ProjectBuilder newProject = new Project.ProjectBuilder();

        final Optional<String> name = consoleHelper.getStringParameter(args, "name");
        newProject.name(name.orElseGet(project::getName));

        final Optional<String> desc = consoleHelper.getStringParameter(args, "desc");
        newProject.description(desc.orElseGet(project::getDescription));

        final Optional<String> beginParam = consoleHelper.getStringParameter(args, "begin");
        final Optional<LocalDateTime> begin = consoleHelper.parseDate(beginParam.orElse(""));
        newProject.begin(begin.orElseGet(project::getBegin));

        final Optional<String> endParam = consoleHelper.getStringParameter(args, "end");
        final Optional<LocalDateTime> end = consoleHelper.parseDate(endParam.orElse(""));
        newProject.end(end.orElseGet(project::getEnd));

        final Optional<String> addTag = consoleHelper.getStringParameter(args, "addTag");
        if (addTag.isPresent()) {
            newProject.addTag(project.getTags().toArray(new Tag[0]));
            newProject.addTag(new Tag(addTag.get()));
        }

        final Optional<String> removeTag = consoleHelper.getStringParameter(args, "removeTag");
        if (removeTag.isPresent()) {
            Collection<Tag> tags = project.getTags();
            tags.remove(new Tag(removeTag.get()));
            newProject.addTag(tags.toArray(new Tag[0]));
        }

        newProject.addTasks(project.getTasks().toArray(new Task[0]));
        todoList.addProject(newProject.build());
    }

}
