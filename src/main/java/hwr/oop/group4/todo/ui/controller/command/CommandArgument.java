package hwr.oop.group4.todo.ui.controller.command;

public class CommandArgument<T> {

    private final String name;
    private final T value;

    public CommandArgument(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }
}
