package hwr.oop.todo.ui;

import java.util.Optional;

public interface MenuResponse {
    boolean isSuccess();
    Optional<String> message();

    Optional<Menu> navigateTo();
}
