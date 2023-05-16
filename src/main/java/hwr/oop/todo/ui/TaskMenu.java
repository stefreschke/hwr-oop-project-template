package hwr.oop.todo.ui;

import hwr.oop.todo.ui.InvalidMenuOptionException;
import hwr.oop.todo.ui.Menu;
import hwr.oop.todo.ui.MenuOption;
import hwr.oop.todo.ui.SelectionResponse;

public class TaskMenu extends Menu {
    public TaskMenu(){
        addOption(new MenuOption( 'a', "Create task"));
        addOption(new MenuOption('b', "Delete task"));
        addOption(new MenuOption('c', "List all tasks"));
    }

    @Override
    public SelectionResponse getSelectionResponse(char selectionKey) {
        try{
            MenuOption option = getOptionByKey(selectionKey);
            return SelectionResponse.success("You selected \""+ option.getDescription()+"\"");
        }catch(InvalidMenuOptionException e){
            return SelectionResponse.error("Selection "+selectionKey+" is invalid.");
        }
    }
}
