package hwr.oop.cards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class TopicTests {

    @Test
    public void canCreateTopicWithName(){

        Topic topic = new Topic("Portugiesisch");

        String testName = topic.getName();
        assertThat(testName).isEqualTo("Portugiesisch");
    }

    @Test
    public void canCreateCard() {

        Topic topic = new Topic("Spanisch");

        topic.createCard("Is 30 dollars too much for Cyberpunk?", "Yes");

        Card testCard = topic.getCardList().get(0);
        assertThat(testCard).isNotNull();
    }
}
