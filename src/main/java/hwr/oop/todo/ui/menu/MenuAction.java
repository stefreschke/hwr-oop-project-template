package hwr.oop.todo.ui.menu;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.menu.responses.MenuResponse;

public class MenuAction {

    private final char key;
    private final String description;
    private final MenuActionHandlerFunction handler;

    public MenuAction(char key, String description, MenuActionHandlerFunction handler){
        this.key = key;
        this.description = description;
        this.handler = handler;
    }

    public char getKey(){
        return key;
    }

    public String getDescription(){
        return description;
    }

    public MenuResponse run(ToDoList toDoList, ParameterProvider parameterProvider){
        return handler.run(toDoList, parameterProvider);
    }

}
