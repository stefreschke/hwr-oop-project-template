package hwr.oop.group4.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TagTest {

    @Test
    void createTag() {
        final Tag tag = new Tag("abc");
        assertThat(tag.getName()).isEqualTo("abc");
    }

    @Test
    void equals() {
        final Tag abcTag = new Tag("abc");
        final Tag abcTag2 = new Tag("abc");
        final Tag differentTag = new Tag("new Tag");

        assertEquals(abcTag, abcTag2);
        assertNotEquals(abcTag, differentTag);
        assertNotEquals(abcTag2, differentTag);
    }
}
