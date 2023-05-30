package hwr.oop;

import hwr.oop.handler.CommandParser;

import java.io.IOException;
import java.io.PrintStream;

import static hwr.oop.util.ConsoleColors.BLUE_BOLD;
import static hwr.oop.util.ConsoleColors.RESET;

public class Main {
    private static final PrintStream out = new PrintStream(System.out);
    public static void renameBucket(ToDoList toDoList, int index, String newBucket) {
        toDoList.renameBucket(index, newBucket);
    }

    public static void sortHelp() {
        out.println("gtd sort [option]");
        out.println("Options:");
        out.println("  priority        - sort by priority");
        out.println("  createdAt       - sort by creation date");
        out.println("  dueDate         - sort by due date"); // TODO
        out.println("  bucket [bucket] - sort by bucket"); // bucket für tag eingesetzt
        out.println("  title           - sort by title"); // TODO
        out.println("  done            - sort by done"); // TODO
        out.println("  help            - print this help");
    }
    public static void exit(ConsoleUserInterface cui, ToDoList toDoList) throws ConsoleUserInterface.CouldNotSaveChangesException {
        out.println("exiting...");
        try {
            toDoList.writeToJSON(cui, toDoList.getFileName());
        } catch (Exception e) {
            throw new ConsoleUserInterface.CouldNotSaveChangesException();
        }
    }
    public static void main(String[] args) throws IOException, ConsoleUserInterface.CouldNotReadInputException {
        ConsoleUserInterface cui = new ConsoleUserInterface(System.out, System.in);
        CommandParser commandParser = new CommandParser(cui);
        ToDoList toDoList = cui.welcome();
        cui.say(BLUE_BOLD + "Please enter a command or type 'gtd help' for more information" + RESET);
        while (true) {
            try {
                if (cui.parseCommands(toDoList, commandParser) == 1) {
                    break;
                }
            } catch (ConsoleUserInterface.CouldNotSaveChangesException e) {
                cui.say("Could not save changes");
            }
        }
    }
}

