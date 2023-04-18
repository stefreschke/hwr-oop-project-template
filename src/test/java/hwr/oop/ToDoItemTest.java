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
        String title = item.title;
        String description = item.description;
        String tag = item.tag;
        boolean done = item.done;
        Priority priority = item.priority;
        assertThat(title).isEqualTo("New Item");
        assertThat(description).isEqualTo("" + "\nCreated " + ToDoItem.getLocalDate());
        assertThat(done).isEqualTo(false);
        assertThat(priority).isEqualTo(Priority.LOW);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Title", "New Title"})
    public void itemTitleSetterStringTest(String title) {
        ToDoItem item = new ToDoItem();
        item.setTitle(title);
        String testTitle = item.title;
        assertThat(testTitle).isEqualTo(title);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Description", "New Description"})
    public void itemDescriptionSetterTest(String description) {
        ToDoItem item = new ToDoItem();
        item.setDescription(description);
        String testDescription = item.description;
        assertThat(testDescription).isEqualTo(description);
    }
    @ParameterizedTest
    @ValueSource(strings = {"Tag", "New Tag"})
    public void itemTagSetterTest(String tag) {
        ToDoItem item = new ToDoItem();
        item.setTag(tag);
        String testTag = item.tag;
        assertThat(testTag).isEqualTo(tag);
    }
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void itemDoneSetterTest(boolean done) {
        ToDoItem item = new ToDoItem();
        item.setDone(done);
        boolean testDone = item.done;
        assertThat(testDone).isEqualTo(done);
    }
    @ParameterizedTest
    @EnumSource(Priority.class)
    public void itemPrioritySetterTest(Priority priority) {
        ToDoItem item = new ToDoItem();
        item.setPriority(priority);
        Priority testPriority = item.priority;
        assertThat(testPriority).isEqualTo(priority);
    }

}



