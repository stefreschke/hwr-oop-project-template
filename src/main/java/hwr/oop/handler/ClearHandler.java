package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;

public class ClearHandler {
    ClearHandler() {
    }
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        clear(toDoList);
    }

    public static void clear(ToDoList toDoList) {
        toDoList.setItems(null);
    }
}
