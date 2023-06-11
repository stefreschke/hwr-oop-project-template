package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ConsoleUserInterface.LogMode;
import hwr.oop.ToDoList;
import hwr.oop.persistence.PersistenceFileNotFoundException;
import hwr.oop.persistence.PersistenceAdapter;


public class ExitHandler implements HandlerCommandsInterface{
    private final PersistenceAdapter persistenceAdapter = new PersistenceAdapter();
    @Override
    public int handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length == 2) {
            if (args[1].equals("exit") || args[1].equals("q")) {
                try {
                    exit(toDoList, cui);
                } catch (ExitProgrammException e) {
                    cui.print(LogMode.NONE, e.getMessage());
                    return 1;
                }
            } else {
                cui.print(LogMode.ERROR, "Could not exit... If that is what you wanted to do, try 'gtd exit'");
            }
        } else {
            cui.print(LogMode.ERROR, "Cannot process additional arguments.");
        }
        return 0;
    }
  
    public void exit(ToDoList toDoList, ConsoleUserInterface cui) throws PersistenceFileNotFoundException, ExitProgrammException {
        cui.print(LogMode.NONE,"Exiting ...");
        try {
            persistenceAdapter.saveData(toDoList);
        } catch (Exception e) {
            cui.print(LogMode.ERROR, "Could not save data.");
        }
        throw new ExitProgrammException();
    }

    public static class ExitProgrammException extends Exception {
        public ExitProgrammException() {
            super("Goodbye!");
        }
    }

}
