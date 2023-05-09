package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.core.TodoList;
import hwr.oop.group4.todo.ui.controller.ConsoleController;
import hwr.oop.group4.todo.ui.controller.ConsoleHelper;
import hwr.oop.group4.todo.ui.controller.command.CommandArgument;

import java.util.Collection;

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
    }

    private void listItems(Collection<CommandArgument<String>> args) {
    }

    private void newItem(Collection<CommandArgument<String>> args) {
    }

    private void removeItem(Collection<CommandArgument<String>> args) {
    }

    private void toTask(Collection<CommandArgument<String>> args) {
    }

}
