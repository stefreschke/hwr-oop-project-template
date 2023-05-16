package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.ui.menu.Menu;

import java.util.Optional;

public class StringResponse implements MenuResponse {
    private final String response;

    public StringResponse(String response){
        this.response = response;
    }

    public static StringResponse with(String response){
        return new StringResponse(response);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public Optional<String> message() {
        return Optional.of(response);
    }

    @Override
    public Optional<Menu> navigateTo() {
        return Optional.empty();
    }


}
