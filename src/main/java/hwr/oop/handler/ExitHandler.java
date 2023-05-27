package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;

public interface ExitHandler {
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        cui.say("Exiting ...");
        toDoList.writeToJSON(cui, toDoList.getFileName());
        System.exit(0);
    }
}
