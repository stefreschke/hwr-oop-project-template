package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;

public class HelpHandler {
    HelpHandler() {
    }
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args ) {
        if (args.length == 2 && (args[1].equals("help") || args[1].equals("h")) ) {
            cui.help();
        } else {
            cui.print(LogMode.WARN, "Could not print help... If that is what you wanted to do, try 'gtd help' or 'gtd h' next time.");
            cui.say("Here is a list of all commands:");
            cui.help();

        }
    }
}
