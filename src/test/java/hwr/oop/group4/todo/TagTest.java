package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagTest {

    @Test
    void createTag() {
        final Tag tag = new Tag("abc");
        assertThat(tag.name()).isEqualTo("abc");
    }
    @Test
    void equalTags(){
        final Tag abcTag = new Tag("abc");
        final Tag abcTag2 = new Tag("abc");

        assertThat(abcTag2).isEqualTo(abcTag);
    }
    @Test
    void notEqualTags(){
        final Tag abcTag = new Tag("abc");
        final Tag differentTag = new Tag("new Tag");

        assertThat(abcTag).isNotEqualTo(differentTag);
    }
}
