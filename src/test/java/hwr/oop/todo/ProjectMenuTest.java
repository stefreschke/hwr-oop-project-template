package hwr.oop.todo;

import hwr.oop.todo.ui.MenuOption;
import hwr.oop.todo.ui.ProjectMenu;
import hwr.oop.todo.ui.SelectionResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectMenuTest {

    @Test
    void hasOptions() {
        ProjectMenu menu = new ProjectMenu();

        assertFalse(menu.getMenuOptions().isEmpty());
    }

    @Test
    void canHandleValidSelections() {
        ProjectMenu menu = new ProjectMenu();
        List<MenuOption> options = menu.getMenuOptions();

        MenuOption validOption = options.get(0);
        SelectionResponse response = menu.getSelectionResponse(validOption.getSelectionKey());

        assertEquals(response.isSuccess(), true);
        assertNotNull(response.getMessage());
    }

    @Test
    void canHandleInvalidSelections(){
        ProjectMenu menu = new ProjectMenu();

        SelectionResponse response = menu.getSelectionResponse('z');

        assertEquals(response.isSuccess(), false);
        assertNotNull(response.getMessage());
    }
}
