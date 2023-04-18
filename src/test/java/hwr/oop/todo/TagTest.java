package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagTest {
    @Test
    void testGetTitle() {
        Tag myTag = new Tag("Beispiel-Tag");
        String title = myTag.getTitle();
        assertThat(title).isNotNull();
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
