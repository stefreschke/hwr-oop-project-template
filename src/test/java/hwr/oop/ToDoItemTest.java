package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class ToDoItemTest {
    @Test
    public void itemConstructorTest() {
        ToDoItem item = new ToDoItem();
        String title = item.getTitle();
        String description = item.getDescription();
        boolean done = item.isDone();
        Priority priority = item.getPriority();
        assertThat(title).isEqualTo("New Item");
        assertThat(description).isEqualTo("");
        assertThat(done).isEqualTo(false);
        assertThat(priority).isEqualTo(Priority.LOW);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Title", "New Title"})
    public void itemTitleSetterStringTest(String title) {
        ToDoItem item = new ToDoItem();
        item.setTitle(title);
        String testTitle = item.getTitle();
        assertThat(testTitle).isEqualTo(title);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Description", "New Description"})
    public void itemDescriptionSetterTest(String description) {
        ToDoItem item = new ToDoItem();
        item.setDescription(description);
        String testDescription = item.getDescription();
        assertThat(testDescription).isEqualTo(description);
    }
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void itemDoneSetterTest(boolean done) {
        ToDoItem item = new ToDoItem();
        item.setDone(done);
        boolean testDone = item.isDone();
        assertThat(testDone).isEqualTo(done);
    }
    @ParameterizedTest
    @EnumSource(Priority.class)
    public void itemPrioritySetterTest(Priority priority) {
        ToDoItem item = new ToDoItem();
        item.setPriority(priority);
        Priority testPriority = item.getPriority();
        assertThat(testPriority).isEqualTo(priority);
    }

}



