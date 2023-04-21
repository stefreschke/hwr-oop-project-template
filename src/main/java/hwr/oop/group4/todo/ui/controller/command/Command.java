package hwr.oop.group4.todo.ui.controller.command;

import java.util.Collection;

public interface Command {

    String getName();
    void call(Collection<Argument<?>> arguments);
}
