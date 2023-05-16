package hwr.oop.todo.ui.menu;

import hwr.oop.todo.ui.menu.responses.MenuResponse;

public class MenuAction {

    private final char key;
    private final String description;
    private final MenuOptionHandlerFunction handler;

    public MenuAction(char key, String description, MenuOptionHandlerFunction handler){
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

    public MenuResponse run(){
        return handler.run();
    }

}
