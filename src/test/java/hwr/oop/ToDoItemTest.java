package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ToDoItemTest {
    @Test
    void getId() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        int result = item.getId();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void getTitle() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        String result = item.getTitle();
        assertThat(result).isEqualTo("testTitle");
    }

    @Test
    void getDescription() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        String result = item.getDescription();
        assertThat(result).isEqualTo("testDesc"+ "\nCreated " + ToDoItem.getLocalDate());
    }

    @Test
    void getTag() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        String result = item.getTag();
        assertThat(result).isEqualTo("testTag");
    }

    @Test
    void isDone() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        boolean result = item.isDone();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void getPriority() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        Priority result = item.getPriority();
        assertThat(result).isEqualTo(Priority.LOW);
    }
}