package hwr.oop;

import hwr.oop.handler.CommandParser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Set;

import static hwr.oop.util.ConsoleColors.BLUE_BOLD;
import static hwr.oop.util.ConsoleColors.RESET;

public class Main {
    private static final PrintStream out = new PrintStream(System.out);
    public static void createBucket(ToDoList toDoList, String newBucket) {
        Set<Bucket> buckets = toDoList.getBuckets();
        buckets.add(new Bucket(newBucket));
    }
    public static void showBuckets(ToDoList toDoList){
        out.println(toDoList.getBuckets());
    }

    public static void editBucket(ToDoList toDoList, int index, String newBucket) {
        toDoList.editBucket(index, newBucket);
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

    public static void handleSort(ToDoList toDoList, String[] commandArray) {
        int nCommands = commandArray.length;
        if (nCommands == 2) {
            sortHelp();
        } else if (nCommands == 3) {
            if (commandArray[2].equals("help")) {
                sortHelp();
            }
        } else if (nCommands == 4) {
            if (commandArray[2].toLowerCase().contains("prio")) {
                if (commandArray[3].equals("asc")) {
                    toDoList.sortByPriority("asc");
                } else {
                    toDoList.sortByPriority("desc");
                }
            } else if (commandArray[2].toLowerCase().contains("create")) {
                if (commandArray[3].equals("asc")) {
                    toDoList.sortByCreatedAt("asc");
                } else {
                    toDoList.sortByCreatedAt("desc");
                }
            } else if (commandArray[2].toLowerCase().contains("bucket")) {
                toDoList.bubbleUpBucket(commandArray[3]);
            } else {
                sortHelp();
            }
        }
    }
    public static void exit(ConsoleUserInterface cui, ToDoList toDoList) throws ConsoleUserInterface.CouldNotSaveChangesException {
        out.println("exiting...");
        try {
            toDoList.writeToJSON(cui, toDoList.getFileName());
        } catch (Exception e) {
            throw new ConsoleUserInterface.CouldNotSaveChangesException();
        }
    }

    public static void initiateSort(ConsoleUserInterface cui, ToDoList toDoList, String[] commandArray) {
        int nCommands = commandArray.length;
        if (nCommands == 2) {
            cui.sortHelp();
        } else if (nCommands == 3) {
            if (commandArray[2].equals("help")) {
                cui.sortHelp();
            }
        } else if (nCommands == 4) {
            assignSortingAlgorithm(cui, toDoList, commandArray);
        }
    }

    public static void assignSortingAlgorithm(ConsoleUserInterface cui, ToDoList toDoList, String[] commandArray) {
        if (commandArray[2].toLowerCase().contains("prio")) {
            if (commandArray[3].equals("asc")) {
                toDoList.sortByPriority("asc");
            } else {
                toDoList.sortByPriority("desc");
            }
        } else if (commandArray[2].toLowerCase().contains("create")) {
            if (commandArray[3].equals("asc")) {
                toDoList.sortByCreatedAt("asc");
            } else {
                toDoList.sortByCreatedAt("desc");
            }
        } else if (commandArray[2].toLowerCase().contains("tag")) {
            toDoList.bubbleUpBucket(commandArray[3]);
        } else {
            cui.sortHelp();
        }
    }
    public static void clear(ToDoList toDoList) {
        toDoList.setItems(null);
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

