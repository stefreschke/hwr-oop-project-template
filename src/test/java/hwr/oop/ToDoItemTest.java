package hwr.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ToDoItemTest {
    @Test
    public void itemTitleSetterStringTest() {
        ToDoItem item = new ToDoItem();
        item.setTitle("Title");
        String testTitle = item.getTitle();
        assertThat(testTitle).isEqualTo("Title");
    }
    @Test
    public void itemDescriptionSetterTest() {
        ToDoItem item = new ToDoItem();
        item.setDescription("Description");
        String testDescription = item.getDescription();
        assertThat(testDescription).isEqualTo("Description");
    }
    @Test
    public void itemDoneSetterTest() {
        ToDoItem item = new ToDoItem();
        item.setDone(true);
        boolean testDone = item.isDone();
        assertThat(testDone).isEqualTo(true);
    }
   @Test
    public void itemPrioritySetterTest() {
        ToDoItem item = new ToDoItem();
        item.setPriority(Priority.HIGH);
        Priority testPriority = item.getPriority();
        assertThat(testPriority).isEqualTo(Priority.HIGH);
    }

}



