package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskMenu extends Menu{
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
