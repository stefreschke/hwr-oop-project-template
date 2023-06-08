package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;

public class ClearHandler {
    ClearHandler() {
    }
    public void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        clear(toDoList);
    }

    public void clear(ToDoList toDoList) {
        toDoList.setItems(null);
    }
}
