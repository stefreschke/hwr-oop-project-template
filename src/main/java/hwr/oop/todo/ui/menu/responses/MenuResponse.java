package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.ui.menu.Menu;

import java.util.Optional;

public interface MenuResponse {
    boolean isSuccess();
    Optional<String> message();

    Optional<Menu> navigateTo();
}
