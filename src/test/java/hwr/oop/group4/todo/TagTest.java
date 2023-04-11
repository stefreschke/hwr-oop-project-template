package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagTest {

    @Test
    void createTag() {
        Tag tag = new Tag("abc");
        assertThat(tag.getName()).isEqualTo("abc");
    }
}
