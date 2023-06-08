package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;
import hwr.oop.persistence.FileNotFoundException;
import hwr.oop.persistence.SavePort;


public class ExitHandler {
    private final SavePort savePort;
    public ExitHandler(SavePort savePort) {
        this.savePort = savePort;
    }
    public void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) throws FileNotFoundException {
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
    public void exit(ToDoList toDoList, ConsoleUserInterface cui) throws FileNotFoundException {
        cui.say("Exiting ...");
        savePort.saveData(toDoList);
        System.exit(0);
    }
}
