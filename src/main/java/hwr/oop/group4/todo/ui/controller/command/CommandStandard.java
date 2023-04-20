package hwr.oop.group4.todo.ui.controller.command;

import java.util.Collection;
import java.util.function.Consumer;

public class CommandStandard implements Command {


    private final String name;
    private final Consumer<Collection<Argument<?>>> consumer;

    public CommandStandard(String name, Consumer<Collection<Argument<?>>> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void call(Collection<Argument<?>> arguments) {
        consumer.accept(arguments);
    }
}
