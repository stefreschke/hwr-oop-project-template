package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectMenu extends Menu{
    public ProjectMenu(){
        addOption(new MenuOption( 'a', "Create project"));
        addOption(new MenuOption('b', "Delete project"));
        addOption(new MenuOption('c', "List all projects"));
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
