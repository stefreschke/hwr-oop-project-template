package hwr.oop.group4.todo.ui.controller.command;

import java.util.Collection;
import java.util.function.Consumer;

public class Command {

    private final String name;
    private final Consumer<Collection<CommandArgument<String>>> consumer;

    public Command(String name, Consumer<Collection<CommandArgument<String>>> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    public String getName() {
        return name;
    }

    public void call(Collection<CommandArgument<String>> arguments) {
        consumer.accept(arguments);
    }
}
