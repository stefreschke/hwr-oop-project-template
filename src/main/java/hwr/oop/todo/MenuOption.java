package hwr.oop.todo;

public class MenuOption {
    private String description;
    private char selectionKey;

    public MenuOption(char selectionKey, String description){
        this.description = description;
        this.selectionKey = selectionKey;
    }

    public String getDescription() {
        return description;
    }

    public char getSelectionKey() {
        return selectionKey;
    }
}
