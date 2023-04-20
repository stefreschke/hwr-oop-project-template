package hwr.oop.group4.todo.ui.controller.menu;

public class EntryArgument {

    private final String name;
    private final String description;

    public EntryArgument(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
