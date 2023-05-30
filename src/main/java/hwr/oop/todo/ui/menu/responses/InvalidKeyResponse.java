package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.ui.menu.Menu;

import java.util.Optional;

public class InvalidKeyResponse implements MenuResponse {
    private char invalidKey;

    private InvalidKeyResponse(char invalidKey){
        this.invalidKey = invalidKey;
    }

    public static InvalidKeyResponse withKey(char key){
        return new InvalidKeyResponse(key);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public Optional<String> message() {
        return Optional.of("The key '"+invalidKey+"' is not valid.");
    }

    @Override
    public Optional<Menu> navigationTarget() {
        return Optional.empty();
    }


}
