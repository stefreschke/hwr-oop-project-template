package hwr.oop.todo;

import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagController;
import hwr.oop.todo.library.tag.TagException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TagControllerTest {
    @Test
    void canGetTagController(){
        TagController tagController = TagController.get();
        Assertions.assertNotNull(tagController);
    }

    @Test
    void canUpdateTagController() {
        TagController tagController = new TagController();
        Tag first = new Tag("Important");
        Tag second = new Tag("Test");
        tagController.createTag(first);
        tagController.createTag(second);

        List<Tag> tagList = tagController.getTags();
        assertEquals(1, Collections.frequency(tagList, first));
        assertEquals(second, tagList.get(1));
    }

    @Test
    void canDeleteTagFromController(){
        TagController tagController = new TagController();
        Tag tag = new Tag("Important");

        tagController.createTag(tag);
        tagController.removeTag(tag);

        List<Tag> tagList = tagController.getTags();
        assertEquals(0, Collections.frequency(tagList, "Important"));
    }

    @Test
    void cannotRemoveTagThatDoesNotExist(){
        TagController controller = new TagController();
        Tag tag = new Tag("Name");

        assertThrows(TagException.class, () -> {
            controller.removeTag(tag);
        });
    }

    @Test
    void cannotCreateTagThatAlreadyExists(){
        TagController controller = new TagController();
        Tag first = new Tag("Name");
        Tag second = new Tag("Name");

        controller.createTag(first);

        assertThrows(TagException.class, () -> {
            controller.createTag(second);
        });
    }
}
