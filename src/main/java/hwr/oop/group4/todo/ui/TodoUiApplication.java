package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.ui.controller.ConsoleController;

public class TodoUiApplication {

    public static void main(String[] args) {
        ConsoleUserInterface ui = new ConsoleUserInterface(new ConsoleController(System.out, System.in));
        ui.mainMenu();
    }
}
