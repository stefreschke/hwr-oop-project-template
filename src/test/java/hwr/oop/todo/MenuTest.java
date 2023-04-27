package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    void canGetMenuOptions() {
        Menu menu = new Menu();
        List<MenuOption> options = menu.getMenuOptions();

        assertNotNull(options);
        assertTrue(options.isEmpty());
    }

    @Test
    void canHandleValidSelections(){
        Menu menu = new Menu();
        menu.addOption(new MenuOption('a', "Example action"));
        List<MenuOption> options = menu.getMenuOptions();


        MenuOption validOption = options.get(0);
        SelectionResponse response = menu.getSelectionResponse(validOption.getSelectionKey());

        assertEquals(response.isSuccess(), true);
        assertNotNull(response.getMessage());
    }

    @Test
    void canHandleInvalidSelections(){
        Menu menu = new Menu();

        SelectionResponse response = menu.getSelectionResponse('z');

        assertEquals(response.isSuccess(), false);
        assertNotNull(response.getMessage());
    }
}
