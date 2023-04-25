package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskMenu implements Menu{
    private List<MenuOption> options;

    public TaskMenu(){
        List<MenuOption> options = new ArrayList<>();

        options.add(new MenuOption( 'a', "Create task"));
        options.add(new MenuOption('b', "Delete task"));
        options.add(new MenuOption('c', "List all tasks"));

        this.options = options;
    }

    @Override
    public List<MenuOption> getMenuOptions() {
        return options;
    }

    private MenuOption getOptionByKey(char key){
        MenuOption option = options.stream().filter(elem -> elem.getSelectionKey() == key).findFirst().orElse(null);

        if(option == null){
            throw new InvalidMenuOptionException("Cannot find option with selection key "+key);
        }

        return option;
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
