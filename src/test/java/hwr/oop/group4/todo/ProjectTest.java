package hwr.oop.group4.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void createTag() {
        Tag tag = new Tag("abc");
        assertThat(tag.getName()).isEqualTo("abc");
    }
}
