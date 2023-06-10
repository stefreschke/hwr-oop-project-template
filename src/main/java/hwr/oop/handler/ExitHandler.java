package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;
import hwr.oop.persistence.PersistenceFileNotFoundException;
import hwr.oop.persistence.PersistenceAdapter;


public class ExitHandler implements HandlerCommandsInterface{
    private final PersistenceAdapter persistenceAdapter = new PersistenceAdapter();
    @Override
    public void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) throws PersistenceFileNotFoundException {
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
    public void exit(ToDoList toDoList, ConsoleUserInterface cui) throws PersistenceFileNotFoundException {
        cui.say("Exiting ...");
        try {
            persistenceAdapter.saveData(toDoList);
        } catch (Exception e) {
            cui.say(e.getMessage());
        }
        System.exit(0);
    }
}
