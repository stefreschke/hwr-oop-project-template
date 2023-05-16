package hwr.oop.todo.ui;

import hwr.oop.todo.ui.menu.Menu;

public class Menus {
    public static Menu HOME = new Menu("Home").on('a', "Print 'Hello World'").navigateTo(Menus.HOME);
}
