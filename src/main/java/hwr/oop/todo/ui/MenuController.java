package hwr.oop.todo.ui;

import hwr.oop.todo.io.IOController;
import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.menu.Menu;

import hwr.oop.todo.ui.menu.responses.MenuResponse;
import java.util.Optional;

public class MenuController {

    private Menu currentMenu = Menus.HOME;
    private final ToDoList toDoList;
    private final IOController ioController;

    public MenuController(ToDoList toDoList, IOController ioController){
        this.toDoList = toDoList;
        this.ioController = ioController;
    }

    public String askForParameter(String parameterName) {
        ioController.printPrompt(parameterName+"?");
        return ioController.getInputString();
    }

    public void execute(){
        ioController.printPrompt(currentMenu.getActions());

        char key = ioController.getInputString().charAt(0);

        MenuResponse menuResponse = currentMenu.handle(key, toDoList, this::askForParameter);

        if(!menuResponse.isSuccess()){
            // Handle error, print to IOController
            ioController.printPrompt("Das hat leider nicht geklappt...");
            execute();
            return;
        }

        // Print optional message
        Optional<String> message = menuResponse.message();

        if(message.isPresent()){
            // Print message
            ioController.printPrompt(message.get());
            execute();
            return;
        }

        // Check if should navigate
        Optional<Menu> nextMenu = menuResponse.navigationTarget();

        if(nextMenu.isPresent()){
            currentMenu = nextMenu.get();
        }

        execute();
    }

}
