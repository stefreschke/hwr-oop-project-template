package hwr.oop.todo;

import java.util.List;

public interface Menu {
    List<MenuOption> getMenuOptions();
    SelectionResponse getSelectionResponse(char selectionKey);
}
