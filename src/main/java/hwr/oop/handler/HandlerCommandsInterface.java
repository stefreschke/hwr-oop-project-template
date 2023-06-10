package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;

public interface HandlerCommandsInterface {
    void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args);
}
