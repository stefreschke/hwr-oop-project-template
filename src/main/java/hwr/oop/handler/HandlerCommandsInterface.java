package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ToDoList;

public interface HandlerCommandsInterface {
    int handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args);
}
