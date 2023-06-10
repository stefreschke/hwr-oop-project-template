package hwr.oop.persistence;

import hwr.oop.ToDoList;

public interface SavePort {
    void saveData(ToDoList toDoList);
}
