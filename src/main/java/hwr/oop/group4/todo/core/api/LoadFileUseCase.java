package hwr.oop.group4.todo.core.api;

import hwr.oop.group4.todo.core.TodoList;

import java.io.File;

public interface LoadFileUseCase {

    TodoList load(File file);
}
