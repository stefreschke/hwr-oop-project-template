package hwr.oop.todo.ui.menu;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.menu.responses.MenuResponse;

public interface MenuActionHandlerFunction {
    MenuResponse run(ToDoList toDoList, ParameterProvider parameterProvider);
}
