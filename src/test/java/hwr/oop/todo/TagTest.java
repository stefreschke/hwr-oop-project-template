package hwr.oop.todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TagTest {
    @Test
    public void testGetTitle() {
        Tag myTag = new Tag("Beispiel-Tag");
        String title = myTag.getTitle();
        Assertions.assertNotEquals(title, null);
    }

    /*
    @Test
    public void testSetTitle() {
        Tag myTag = new Tag("Beispiel-Tag");
        myTag.setTitle("Neuer Titel");
        String title = myTag.getTitle();
        Assertions.assertEquals("Neuer Titel", title);
    }
    */
}
