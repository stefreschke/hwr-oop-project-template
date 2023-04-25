package hwr.oop.group4.todo.ui.controller.command;

import java.util.Collection;
import java.util.function.Consumer;

public class Command {

    private final String name;
    private final Consumer<Collection<Argument<?>>> consumer;

    public Command(String name, Consumer<Collection<Argument<?>>> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    public String getName() {
        return name;
    }

    public void call(Collection<Argument<?>> arguments) {
        consumer.accept(arguments);
    }
}
