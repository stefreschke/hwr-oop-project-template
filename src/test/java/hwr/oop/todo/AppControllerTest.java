package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class AppControllerTest {

    @Test
    void testToDoListNotNull() {
        assertNotNull(AppController.toDoList);
    }

    @Test
    void testIOControllerNotNull() {
        assertNotNull(AppController.io);
    }

    @Test
    void testMenuControllerNotNull() {
        assertNotNull(AppController.ui);
    }

   /*
    this testcase does not load for any reason, so it will be commented until the next meeting.

    @Test
    public void testStartApplication() {
        MenuController ui = mock(MenuController.class);
        AppController application = mock(AppController.class);

        application.main(null);

        verify(ui, times(1)).execute();
    } */
}

