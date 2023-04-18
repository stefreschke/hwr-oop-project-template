package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.TodoList;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUserInterface {

    private final PrintStream out;
    private final Scanner in;
    private TodoList todoList;

    public ConsoleUserInterface(OutputStream out, InputStream in) {
        this.out = new PrintStream(out);
        this.in = new Scanner(in);
        todoList = load();
    }

    public void mainMenu() {
        final String message = "Main menu:\n" + // TODO: print bold
                "quit\n" +
                "save\n" +
                "load\n" +
                "intray\n" +
                "tasks\n" +
                "projects\n" +
                "calendar";
        out.println(message);
        while (true) {
            String input = in.nextLine();

            switch (input) {
                case "quit":
                    return;
                case "save":
                    save();
                    break;
                case "load":
                    todoList = load();
                    break;
                case "intray":
                    intray();
                    break;
                case "tasks":
                    tasks();
                    break;
                case "projects":
                    projects();
                    break;
                case "calendar":
                    calendar();
                    break;
                default:
                    out.println(message);
                    break;
            }
        }
    }

    private void save() {
    }

    private TodoList load() {
        out.println("Do want to 'load' from file or create 'new' list?");
        while (true) {
            String input = in.nextLine();
            if (input.equals("new")) {
                return new TodoList();
            } else if (input.equals("load")) {
                // TODO: load from file
                return null;
            }
            out.println("Please enter 'new' or 'load'.");
        }
    }

    private void intray() {
    }

    private void tasks() {
    }

    private void projects() {
    }

    private void calendar() {
    }
}
