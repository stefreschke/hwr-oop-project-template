package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TagMenuTest {

    @Test
    void hasOptions() {
        TagMenu menu = new TagMenu();

        assertFalse(menu.getMenuOptions().isEmpty());
    }

    @Test
    void canHandleValidSelections() {
        TagMenu menu = new TagMenu();
        List<MenuOption> options = menu.getMenuOptions();

        MenuOption validOption = options.get(0);
        SelectionResponse response = menu.getSelectionResponse(validOption.getSelectionKey());

        assertEquals(response.isSuccess(), true);
        assertNotNull(response.getMessage());
    }

    @Test
    void canHandleInvalidSelections(){
        TagMenu menu = new TagMenu();

        SelectionResponse response = menu.getSelectionResponse('z');

        assertEquals(response.isSuccess(), false);
        assertNotNull(response.getMessage());
    }
}
