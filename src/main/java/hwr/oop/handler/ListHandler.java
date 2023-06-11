package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ConsoleUserInterface.LogMode;
import hwr.oop.ToDoItem;
import hwr.oop.ToDoList;

import java.util.List;

public class ListHandler implements HandlerCommandsInterface{
    public ListHandler() {
    }
    @Override
    public int handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length == 2 && (args[1].equals("list") || args[1].equals("ls")) ) {
            list(toDoList, cui);
        } else {
            cui.print(LogMode.WARN, "Could not list... If that is what you wanted to do, try 'gtd list'");
        }
        return 0;
    }
    public void list(ToDoList toDoList, ConsoleUserInterface cui) {
        cui.print(LogMode.NONE, toDoList.getName() + ":");
        List<ToDoItem> toDoItems = toDoList.getItems();
        if (toDoItems == null || toDoItems.isEmpty()) {
            cui.print(LogMode.NONE, "👀Looks Empty here... Add some tasks!");
            return;
        }
        for(ToDoItem toDoItem:toDoItems) cui.print(LogMode.NONE, toDoItem.toString());
    }
}
