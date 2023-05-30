package hwr.oop.todo;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.*;
import hwr.oop.todo.ui.menu.Menu;
import hwr.oop.todo.ui.menu.MenuAction;
import hwr.oop.todo.ui.menu.responses.InvalidKeyResponse;
import hwr.oop.todo.ui.menu.responses.MenuResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private static ToDoList toDoList;

    @BeforeAll
    public static void init(){
        toDoList = new ToDoList();
    }

    @Test
    void canCreateMenuWithoutActions() {
        Menu menu = new Menu();

        Collection<MenuAction> actions = menu.getActions();

        assertEquals(0, actions.size());
    }

    @Test
    void canCreateMenuWithActions() {
        Menu menu = new Menu().on('a', "DescA").execute((toDoList, parameterProvider) -> null);

        Collection<MenuAction> actions = menu.getActions();

        assertEquals(1, actions.size());
    }

    @Test
    void canHandleKeyEntry() {
        Menu menu = new Menu().on('a', "DescA").execute((toDoList, parameterProvider) -> new MenuResponse() {
            @Override
            public boolean isSuccess() {
                return true;
            }

            @Override
            public Optional<String> message() {
                return Optional.empty();
            }

            @Override
            public Optional<Menu> navigationTarget() {
                return Optional.empty();
            }
        });

        MenuResponse response = menu.handle('a', toDoList);

        assertTrue(response.isSuccess());
    }

    @Test
    void cannotHandleInvalidEntries() {
        Menu menu = new Menu().on('a', "DescA").execute((toDoList, parameterProvider) -> null);

        MenuResponse response = menu.handle('z', toDoList);

        assertInstanceOf(InvalidKeyResponse.class, response);
        assertFalse(response.isSuccess());
        assertFalse(response.message().isEmpty());
        assertTrue(response.navigationTarget().isEmpty());
    }

    @Test
    void canReturnNavigationResponse() {
        Menu menu = new Menu().on('a', "DescA").navigateTo(Menus.HOME);

        MenuResponse response = menu.handle('a', toDoList);

        assertTrue(response.navigationTarget().isPresent());
        assertEquals(Menus.HOME, response.navigationTarget().get());
    }

    @Test
    void canReturnPrintStringResponse() {
        Menu menu = new Menu().on('a', "DescA").printString("Hello World");

        MenuResponse response = menu.handle('a', toDoList);

        assertTrue(response.message().isPresent());
        assertEquals("Hello World", response.message().get());
    }
}
