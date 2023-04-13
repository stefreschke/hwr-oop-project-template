package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class TagControllerTest {
    @Test
    void CanGetTagController(){
        TagController tagController = TagController.get();
        Assertions.assertNotNull(tagController);
    }

    @Test
    void CanUpdateTagController() {
        TagController tagController = TagController.get();
        Task task = new Task("Title", "Description");
        task.addTag("Important");
        task.addTag("Test");

        List<String> tagList = tagController.getTags();
        Assertions.assertEquals(1, Collections.frequency(tagList, "Important"));
        Assertions.assertEquals("Test", tagList.get(tagList.size() - 1));
    }

    @Test
    void CanDeleteTagFromController() {
        TagController tagController = TagController.get();
        tagController.removeTag("Important");

        List<String> tagList = tagController.getTags();
        Assertions.assertEquals(0, Collections.frequency(tagList, "Important"));
    }
}
