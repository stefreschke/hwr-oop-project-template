package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;

public interface SortHandler {
    static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        initiateSort(cui, toDoList, args);
    }
    static void initiateSort(ConsoleUserInterface cui, ToDoList toDoList, String[] commandArray) {
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
    static void assignSortingAlgorithm(ConsoleUserInterface cui, ToDoList toDoList, String[] commandArray) {
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

}