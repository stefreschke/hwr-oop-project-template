package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;

public interface ClearHandler {
    static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        clear(toDoList);
    }

    static void clear(ToDoList toDoList) {
        toDoList.setItems(null);
    }
}
