package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskMenuTest {

    @Test
    void canGetMenuOptions() {
        TaskMenu menu = new TaskMenu();
        List<MenuOption> options = menu.getMenuOptions();

        assertNotNull(options);
        assertTrue(!options.isEmpty());
    }

    @Test
    void canHandleValidSelections(){
        TaskMenu menu = new TaskMenu();
        List<MenuOption> options = menu.getMenuOptions();

        MenuOption validOption = options.get(0);
        SelectionResponse response = menu.getSelectionResponse(validOption.getSelectionKey());

        assertEquals(response.isSuccess(), true);
        assertNotNull(response.getMessage());
    }

    @Test
    void canHandleInvalidSelections(){
        TaskMenu menu = new TaskMenu();

        SelectionResponse response = menu.getSelectionResponse('z');

        assertEquals(response.isSuccess(), false);
        assertNotNull(response.getMessage());
    }
}
