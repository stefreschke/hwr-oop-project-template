package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.core.TodoList;
import hwr.oop.group4.todo.ui.controller.ConsoleController;
import hwr.oop.group4.todo.ui.controller.command.Command;
import hwr.oop.group4.todo.ui.controller.command.CommandArgument;
import hwr.oop.group4.todo.ui.controller.menu.Entry;
import hwr.oop.group4.todo.ui.controller.menu.Menu;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleUserInterface {

    private final ConsoleController consoleController;
    private final ProjectUi projectUi;
    private TodoList todoList;

    public ConsoleUserInterface(ConsoleController consoleController) {
        this.consoleController = consoleController;
        projectUi = new ProjectUi(this.consoleController);
        load(null);
    }

    public void mainMenu() {
        Menu menu = new Menu("Main Menu", "Welcome to ToDo!", List.of(
                new Entry("intray",   ""),
                new Entry("tasks",    ""),
                new Entry("projects", ""),
                new Entry("calendar", ""),
                new Entry("load",     ""),
                new Entry("save",     ""),
                new Entry("quit",     "Quit the program.")
        ));

        AtomicBoolean shouldReturn = new AtomicBoolean(false);
        while (!shouldReturn.get()) {
            consoleController.output(menu.toString());

            consoleController.inputOptions(List.of("main"), List.of(
                    new Command("intray",   args -> {}),
                    new Command("tasks",    args -> {}),
                    new Command("projects", args -> projectUi.menu(todoList)),
                    new Command("calendar", args -> {}),
                    new Command("load",     this::load),
                    new Command("save",     this::save),
                    new Command("quit",     args -> shouldReturn.set(true))
            ), new Command("wrongInput", args -> {}));
        }
    }

    private void save(Collection<CommandArgument<String>> args) {
    }

    private void load(Collection<CommandArgument<String>> args) {
        String question = "Do you want to load from a file? (Otherwise create an empty todo list)";
        boolean loadFromFile = consoleController.inputBool(List.of("main", "load"), question, false);

        if (loadFromFile) {
            // TODO: load from file
            todoList = new TodoList();
        } else {
            todoList = new TodoList();
        }
    }

}
