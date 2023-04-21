package hwr.oop.tag;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TagTest {

    @Test
    void canGetTitle() {
        final String title = "Title";
        final Tag tag = new Tag(title);
        assertThat(tag.getTitle()).isEqualTo(title);
    }
}
