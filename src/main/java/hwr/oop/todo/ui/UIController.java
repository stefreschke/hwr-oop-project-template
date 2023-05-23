package hwr.oop.todo.ui;

import hwr.oop.todo.ui.menu.Menu;
import hwr.oop.todo.ui.menu.responses.MenuResponse;

import java.util.Optional;

public class UIController {

    private Menu currentMenu = Menus.HOME;

    public void execute(){
        MenuResponse menuResponse = currentMenu.handle('a');

        if(!menuResponse.isSuccess()){
            // Handle error, print to IOController
            return;
        }

        // Print optional message
        Optional<String> message = menuResponse.message();

        if(message.isPresent()){
            // Print message
            return;
        }

        // Check if should navigate
        Optional<Menu> nextMenu = menuResponse.navigateTo();

        if(nextMenu.isPresent()){
            currentMenu = nextMenu.get();
        }


    }

}
