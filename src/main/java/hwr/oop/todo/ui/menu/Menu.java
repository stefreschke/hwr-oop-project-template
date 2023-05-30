package hwr.oop.todo.ui.menu;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.menu.responses.MenuResponse;
import hwr.oop.todo.ui.menu.responses.MenuResponseInContext;
import hwr.oop.todo.ui.menu.responses.InvalidKeyResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Menu {
    private final Map<Character, MenuAction> actions = new HashMap<>();

    public MenuResponseInContext on(char key, String description) {
        return new MenuResponseInContext(key, description, this);
    }

    public void addAction(char key, String description, MenuActionHandlerFunction handler){
        MenuAction menuAction = new MenuAction(key, description, handler);
        actions.put(key, menuAction);
    }
    public ArrayList<MenuAction> getActions(){
        return new ArrayList<>(actions.values());
    }



    public MenuResponse handle(char key, ToDoList toDoList, ParameterProvider parameters){
        if(!actions.containsKey(key)){
            return InvalidKeyResponse.withKey(key);
        }

        return actions.get(key).run(toDoList, parameters);
    }

    public MenuResponse handle(char key, ToDoList toDoList){
        return this.handle(key, toDoList, (name) -> "");
    }

}
