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

}
