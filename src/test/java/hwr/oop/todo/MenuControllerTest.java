package hwr.oop.todo;

import hwr.oop.todo.io.IOController;
import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.MenuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuControllerTest {

    private ToDoList mockToDoList;
    private IOController mockIOController;
    private MenuController menuController;

    @BeforeEach
    void setUp() {
        mockToDoList = mock(ToDoList.class);
        mockIOController = mock(IOController.class);
        menuController = new MenuController(mockToDoList, mockIOController);
    }

    @Test
    void testAskForParameter() {
        String parameterName = "testParameter";
        String expectedInput = "Test Input";
        when(mockIOController.getInputString()).thenReturn(expectedInput);

        String result = menuController.askForParameter(parameterName);

        verify(mockIOController).printPrompt(parameterName + "?");
        verify(mockIOController).getInputString();
        assertEquals(expectedInput, result);
    }

}




