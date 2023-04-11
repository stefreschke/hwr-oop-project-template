package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ToDoItemTest {
    @Test
    public void itemConstructorTest() {
        ToDoItem item = new ToDoItem();
        String title = item.title;
        String description = item.description;
        boolean done = item.done;
        Priority priority = item.priority;
        Assertions.assertThat(title).isEqualTo("New Item");
        Assertions.assertThat(description).isEqualTo("");
        Assertions.assertThat(done).isEqualTo(false);
        Assertions.assertThat(priority).isEqualTo(Priority.LOW);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Title", "New Title"})
    public void itemTitleSetterStringTest(String title) {
        ToDoItem item = new ToDoItem();
        item.setTitle(title);
        String testTitle = item.title;
        Assertions.assertThat(testTitle).isEqualTo(title);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Description", "New Description"})
    public void itemDescriptionSetterTest(String description) {
        ToDoItem item = new ToDoItem();
        item.setDescription(description);
        String testDescription = item.description;
        Assertions.assertThat(testDescription).isEqualTo(description);
    }
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void itemDoneSetterTest(boolean done) {
        ToDoItem item = new ToDoItem();
        item.setDone(done);
        boolean testDone = item.done;
        Assertions.assertThat(testDone).isEqualTo(done);
    }
    @ParameterizedTest
    @EnumSource(Priority.class)
    public void itemPrioritySetterTest(Priority priority) {
        ToDoItem item = new ToDoItem();
        item.setPriority(priority);
        Priority testPriority = item.priority;
        Assertions.assertThat(testPriority).isEqualTo(priority);
    }

}



