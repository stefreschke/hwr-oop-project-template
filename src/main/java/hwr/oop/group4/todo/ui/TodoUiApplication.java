package hwr.oop.group4.todo.ui;

public class TodoUiApplication {

    public static void main(String[] args) {
        ConsoleUserInterface ui = new ConsoleUserInterface(System.out, System.in);
        ui.mainMenu();
    }
}
