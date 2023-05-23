package hwr.oop.todo.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenusTest {
    @Test
    void canInstantiateUtilityClass() {
        assertThrows(IllegalStateException.class, Menus::new);
    }
}