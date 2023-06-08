package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;

public class SortHandler {
    SortHandler() {
    }
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        initiateSort(cui, toDoList, args);
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
        } else if (commandArray[2].toLowerCase().contains("tag")) {
            toDoList.bubbleUpBucket(commandArray[3]);
        } else {
            cui.sortHelp();
        }
    }

}