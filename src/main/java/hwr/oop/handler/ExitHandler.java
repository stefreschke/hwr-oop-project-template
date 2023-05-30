package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;

public interface ExitHandler {
    static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length == 2) {
            if (args[1].equals("exit") || args[1].equals("q")) {
                exit(toDoList, cui);
            } else {
                cui.print(LogMode.ERROR, "Could not exit... If that is what you wanted to do, try 'gtd exit'");
            }
        } else {
            cui.print(LogMode.ERROR, "Cannot process additional arguments.");
        }
    }
    static void exit(ToDoList toDoList, ConsoleUserInterface cui) {
        cui.say("Exiting ...");
        toDoList.writeToJSON(cui, toDoList.getFileName());
        System.exit(0);
    }
}
