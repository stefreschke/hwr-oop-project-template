package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;

public class ExitHandler {
    public ExitHandler() {
    }
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) throws ToDoList.FileNotFoundAndCoundNotCreateException, ExitProgrammException {
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
    public static void exit(ToDoList toDoList, ConsoleUserInterface cui) throws ToDoList.FileNotFoundAndCoundNotCreateException, ExitProgrammException {
        cui.print(LogMode.NONE,"Exiting ...");
        toDoList.writeToJSON(cui, toDoList.getFileName());
        throw new ExitProgrammException();
    }

    public static class ExitProgrammException extends Exception {
        public ExitProgrammException() {
            super("Goodbye!");
        }
    }
}
