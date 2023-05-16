package hwr.oop.todo.ui;

public class MenuResponseInContext {

    private final char key;
    private final String description;
    private final Menu menu;

    public MenuResponseInContext(char key, String description, Menu menu){
        this.key = key;
        this.description = description;
        this.menu = menu;
    }

    private void registerHandler(MenuOptionHandlerFunction handler){
        menu.addAction(key, description, handler);
    }

    public Menu printString(String s){
        registerHandler(() -> StringResponse.with(s));
        return menu;
    }

    public Menu navigateTo(Menu nextMenu){
        registerHandler(() -> NavigationResponse.to(nextMenu));
        return menu;
    }

    public Menu execute(MenuOptionHandlerFunction handler){
        registerHandler(handler);
        return menu;
    }

}
