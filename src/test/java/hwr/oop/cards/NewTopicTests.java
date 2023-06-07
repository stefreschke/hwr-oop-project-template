package hwr.oop.cards;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewTopicTests {

    @Test
    public void canCreateTopicWithName(){

        Topic topic = new Topic("Portugiesisch");

        String testName = topic.getName();
        assertThat(testName).isEqualTo("Portugiesisch");
    }
}
