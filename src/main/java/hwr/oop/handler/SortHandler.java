package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;

public class SortHandler {
    public SortHandler() {
    }
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        initiateSort(cui, toDoList, args);
    }
    public static void initiateSort(ConsoleUserInterface cui, ToDoList toDoList, String[] commandArray) {
        int nCommands = commandArray.length;
        if (nCommands == 2) {
            sortHelp(cui);
        } else if (nCommands == 3) {
            if (commandArray[2].equals("help")) {
                sortHelp(cui);
            }
        } else if (nCommands == 4) {
            assignSortingAlgorithm(cui, toDoList, commandArray);
        }
    }
    //might need a rework: cognitive complexity > 15
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
        } else if (commandArray[2].toLowerCase().contains("title")) {
            if (commandArray[3].equals("asc")) {
                toDoList.sortByTitle("asc");
            } else {
                toDoList.sortByTitle("desc");
            }
        } else if (commandArray[2].toLowerCase().contains("done")) {
            if (commandArray[3].equals("asc")) {
                toDoList.sortByDone("asc");
            } else {
                toDoList.sortByDone("desc");
            }
        } else if (commandArray[2].toLowerCase().contains("due")) {
            if (commandArray[3].equals("asc")) {
                toDoList.sortByDueDate("asc");
            } else {
                toDoList.sortByDueDate("desc");
            }
        } else if (commandArray[2].toLowerCase().contains("bucket")) {
            toDoList.bubbleUpBucket(commandArray[3]);
        } else {
            sortHelp(cui);
        }
    }
    public static void sortHelp(ConsoleUserInterface cui) {
        cui.print(LogMode.NONE,"gtd sort [option]");
        cui.print(LogMode.NONE,"Options:");
        cui.print(LogMode.NONE,"  priority - sort by priority");
        cui.print(LogMode.NONE,"  createdAt- sort by creation date");
        cui.print(LogMode.NONE,"  dueDate  - sort by due date");
        cui.print(LogMode.NONE,"  bucket [bucket]- sort by bucket");
        cui.print(LogMode.NONE,"  title    - sort by title");
        cui.print(LogMode.NONE,"  done     - sort by done");
        cui.print(LogMode.NONE,"  help     - print this help");
    }
}