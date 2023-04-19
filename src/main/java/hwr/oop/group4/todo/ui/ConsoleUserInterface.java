package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.TodoList;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUserInterface {

    private final PrintStream out;
    private final Scanner in;
    private final DialogHelper dialogHelper;
    private final ProjectUi projectUi;
    private TodoList todoList;

    public ConsoleUserInterface(OutputStream out, InputStream in) {
        this.out = new PrintStream(out);
        this.in = new Scanner(in);
        dialogHelper = new DialogHelper(this.out, this.in);
        projectUi = new ProjectUi(this.out, this.in);
        load();
    }

    public void mainMenu() {
        Map<String, String> options = new HashMap<>();
        options.put("intray",   "");
        options.put("tasks",    "");
        options.put("projects", "");
        options.put("calendar", "");
        options.put("load",     "");
        options.put("save",     "");
        options.put("quit",     "Quit the program.");

        while (true) {
            String input = dialogHelper.getMenuSelectionFromUser("Main Menu", "main> ", options);

            switch (input) {
                case "intray":
                    intray();
                    break;
                case "tasks":
                    tasks();
                    break;
                case "projects":
                    projectUi.menu(todoList);
                    break;
                case "calendar":
                    calendar();
                    break;
                case "load":
                    load();
                    break;
                case "save":
                    save();
                    break;
                case "quit":
                    return;
                default:
                    break;
            }
        }
    }

    private void save() {
    }

    private void load() {
        String question = "Do you want to load from a file? (Otherwise create an empty todo list)";
        boolean loadFromFile = dialogHelper.getYesNoFromUser(question, false);

        if (loadFromFile) {
            // TODO: load from file
            todoList = new TodoList();
        } else {
            todoList = new TodoList();
        }
    }

    private void intray() {
    }

    private void tasks() {
    }

    private void calendar() {
    }
}
