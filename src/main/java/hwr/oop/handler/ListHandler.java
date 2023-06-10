package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;

public class ListHandler implements HandlerCommandsInterface{
    ListHandler() {
    }
    @Override
    public void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length == 2 && (args[1].equals("list") || args[1].equals("ls")) ) {
            cui.list(toDoList);
        } else {
            cui.print(LogMode.WARN, "Could not list... If that is what you wanted to do, try 'gtd list'");
        }
    }
}
