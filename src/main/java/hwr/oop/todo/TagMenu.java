package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;

public class TagMenu extends Menu{
    private List<MenuOption> options;

    public TagMenu(){
        addOption(new MenuOption( 'a', "Create tag"));
        addOption(new MenuOption('b', "Delete tag"));
        addOption(new MenuOption('c', "List all tags"));
    }

    @Override
    public SelectionResponse getSelectionResponse(char selectionKey) {
        try{
            MenuOption option = getOptionByKey(selectionKey);
            return SelectionResponse.success("You selected \""+ option.getDescription()+"\" in TagMenu");
        }catch(InvalidMenuOptionException e){
            return SelectionResponse.error("Selection "+selectionKey+" is invalid.");
        }
    }
}
