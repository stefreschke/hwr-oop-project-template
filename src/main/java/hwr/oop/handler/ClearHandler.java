package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ToDoList;

public class ClearHandler implements HandlerCommandsInterface {
    public ClearHandler() {
    }
    @Override
    public int handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        clear(toDoList);
        return 0;
    }

    public void clear(ToDoList toDoList) {
        toDoList.setItems(null);
    }
}
