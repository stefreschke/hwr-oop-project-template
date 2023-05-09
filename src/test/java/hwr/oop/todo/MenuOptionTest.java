package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuOptionTest {

    @Test
    void canCreateMenuOption(){
        MenuOption menuOption = new MenuOption('a', "Description");

        assertEquals(menuOption.getSelectionKey(), 'a');
        assertEquals(menuOption.getDescription(), "Description");
    }

}
