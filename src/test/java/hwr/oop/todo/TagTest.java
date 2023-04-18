package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void CanCreateTagWithNameOnly(){
        Tag tag = new Tag("Name");
        Assertions.assertEquals("Name", tag.getName());
    }

    @Test
    public void CanCreateTagWithDescription(){
        Tag tag = new Tag("Name", "Description");
        Assertions.assertEquals("Name", tag.getName());
        Assertions.assertEquals("Description", tag.getDescription());
    }

    @Test
    void TagsAreEqualWithEqualTitles(){
        Tag first = new Tag("First");
        Tag second = new Tag("First");

        Assertions.assertEquals(first, second);
    }

    @Test
    void TagsAreEqualWithEqualContents(){
        Tag first = new Tag("Name", "Description");
        Tag second = new Tag("Name", "Description");

        Assertions.assertEquals(first, second);
    }

    @Test
    void TagsAreNotEqualWithDifferentContents(){
        Tag first = new Tag("Name_", "Description");
        Tag second = new Tag("Name", "Description");

        Assertions.assertNotEquals(first, second);
    }

}
