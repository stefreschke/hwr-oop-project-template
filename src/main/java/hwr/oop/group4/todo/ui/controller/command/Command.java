package hwr.oop.group4.todo.ui.controller.command;

import java.util.Collection;
import java.util.List;

public interface Command {

    String getName();
    void call(Collection<Argument<?>> arguments);
}
