package hwr.oop.todo.ui;

import hwr.oop.todo.ui.menu.Menu;

public class Menus {
    public static final Menu HOME = new Menu().on('a', "Print 'Hello World'").navigateTo(Menus.HOME);

    Menus() {
        throw new IllegalStateException("Utility class");
    }
}
