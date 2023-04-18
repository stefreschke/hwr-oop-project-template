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
    void CanUpdateTagController() throws TagError {
        TagController tagController = new TagController();
        Tag first = new Tag("Important");
        Tag second = new Tag("Test");
        tagController.createTag(first);
        tagController.createTag(second);

        List<Tag> tagList = tagController.getTags();
        Assertions.assertEquals(1, Collections.frequency(tagList, first));
        Assertions.assertEquals(second, tagList.get(1));
    }

    @Test
    void CanDeleteTagFromController() throws TagError {
        TagController tagController = new TagController();
        Tag tag = new Tag("Important");

        tagController.createTag(tag);
        tagController.removeTag(tag);

        List<Tag> tagList = tagController.getTags();
        Assertions.assertEquals(0, Collections.frequency(tagList, "Important"));
    }
}
