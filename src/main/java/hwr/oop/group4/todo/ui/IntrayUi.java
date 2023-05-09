package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.commons.exceptions.TodoRuntimeException;
import hwr.oop.group4.todo.core.Idea;
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

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class IntrayUi {

    private final ConsoleController consoleController;
    private final ConsoleHelper consoleHelper;
    private TodoList todoList;

    public IntrayUi(ConsoleController consoleController) {
        this.consoleController = consoleController;
        this.consoleHelper = new ConsoleHelper();
    }

    public void menu(TodoList todoList) {
        this.todoList = todoList;
        listItems(null);

        Menu menu = new Menu("Intray Menu", "Manage your fleeting thoughts!", List.of(
                new Entry("list", "List all ideas."),
                new Entry("new", "Create a new idea."),
                new Entry("remove", "Remove an idea", List.of(
                        new EntryArgument("id <id>", "ID of the idea to be removed.")
                )),
                new Entry("task", "Create a task from an idea", List.of(
                        new EntryArgument("id <id>", "ID of the idea to be used.")
                )),
                new Entry("back", "Return to the previous menu.")
        ));
        consoleController.output(menu.toString());

        AtomicBoolean shouldReturn = new AtomicBoolean(false);
        while (!shouldReturn.get()) {
            consoleController.inputOptions(List.of("intray"), List.of(
                    new Command("list", this::listItems),
                    new Command("new", this::newItem),
                    new Command("remove", this::removeItem),
                    new Command("task", this::toTask),
                    new Command("back", args -> shouldReturn.set(true))
            ), new Command("wrongInput", args -> {}));
        }
    }

    private void listItems(Collection<CommandArgument<String>> args) {
        final List<Idea> ideas = todoList.getInTray().stream().toList();
        final int idColumnLength = Math.max((int) Math.ceil(Math.log10(ideas.size()) - 2), 2);
        final Table ideaTable = new Table(List.of(
                new ColumnConfig("ID", idColumnLength),
                new ColumnConfig("Name", 20),
                new ColumnConfig("Description", 50)
        ));

        for (int i = 0; i < ideas.size(); i++) {
            final Idea idea = ideas.get(i);
            ideaTable.addRow(
                    String.valueOf(i),
                    idea.name(),
                    idea.description()
            );
        }

        consoleController.output(ideaTable.toString());
    }

    private void newItem(Collection<CommandArgument<String>> args) {
        final String name = consoleController.input(List.of("intray", "new", "name")).orElseThrow();
        final String desc = consoleController.input(List.of("intray", "new", "description")).orElse("");

        todoList.addIdea(new Idea(name, desc));
    }

    private void removeItem(Collection<CommandArgument<String>> args) {
        final int id;
        try {
            id = consoleHelper.getId(args, todoList.getInTray().stream().toList().size());
        } catch (TodoRuntimeException e) {
            consoleController.outputLine(e.getMessage());
            return;
        }

        final String ideaName = todoList.getInTray().stream().toList().get(id).name();
        final String confirmation = "Do you really want to remove " + ideaName + "?";
        if (consoleController.inputBool(List.of("intray", "remove"), confirmation, false)) {
            todoList.removeIdea(todoList.getInTray().stream().toList().get(id));
        }
    }

    private void toTask(Collection<CommandArgument<String>> args) {
        // call TaskUi
    }

}
