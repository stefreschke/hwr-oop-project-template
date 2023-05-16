package hwr.oop.todo.ui.menu;

import hwr.oop.todo.ui.menu.responses.MenuResponse;
import hwr.oop.todo.ui.menu.responses.MenuResponseInContext;
import hwr.oop.todo.ui.menu.responses.InvalidKeyResponse;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Menu {
    private final String menuName;
    private final Map<Character, MenuAction> actions = new HashMap<>();

    public Menu(String menuName){
        this.menuName = menuName;
    }

    public MenuResponseInContext on(char key, String description) {
        return new MenuResponseInContext(key, description, this);
    }

    public void addAction(char key, String description, MenuOptionHandlerFunction handler){
        MenuAction menuAction = new MenuAction(key, description, handler);
        actions.put(key, menuAction);
    }
    public Collection<MenuAction> getActions(){
        return actions.values();
    }

    public MenuResponse handle(char key){
        if(!actions.containsKey(key)){
            return InvalidKeyResponse.withKey(key);
        }

        return actions.get(key).run();
    }

}
