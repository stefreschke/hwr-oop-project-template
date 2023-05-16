package hwr.oop.todo.ui;

public class Menus {
    public static Menu HOME = new Menu("Home").on('a', "Print 'Hello World'").navigateTo(Menus.HOME);
}
