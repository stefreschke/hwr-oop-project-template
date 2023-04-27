package hwr.oop.group4.todo.core.api;

import hwr.oop.group4.todo.core.TodoList;

import java.io.File;

public interface SaveFileUseCase {

    void save(TodoList todoList, File file);
}
