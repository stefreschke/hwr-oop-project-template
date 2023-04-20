package hwr.oop.group4.todo.ui.controller.command;

import java.util.Collection;
import java.util.List;

public class CommandNoArgs implements Command {

    private final String name;
    private final Runnable runnable;

    public CommandNoArgs(String name, Runnable runnable) {
        this.name = name;
        this.runnable = runnable;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void call(Collection<Argument<?>> arguments) {
        runnable.run();
    }
}
