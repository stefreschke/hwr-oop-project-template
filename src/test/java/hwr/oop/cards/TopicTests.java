package hwr.oop.cards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class TopicTests {
    @Test
    void canGetTopicName(){

        Topic topic = new Topic("Japanese", 0);
        String name = topic.getName();

        assertThat(name).isEqualTo("Japanese");
    }

    @Test
    void checkActiveBox(){

        Topic topic = new Topic("Spanish", 3);

        assertThat(topic.getActiveBox()).isZero();
    }

    @Test
    void canMoveCardFromBoxToBox(){

        Box firstBox = new Box();
        Box secondBox = new Box();
        Card card = new Card("Is 30 dollars too much for Cyberpunk?", "Yes");

        Topic topic = new Topic("Games", 0);

        // TODO
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 7})
    void createTopicWithXAmountOfBoxes(int amount){

        Topic topic = new Topic("Spanish", amount);

        assertThat(topic.boxCount()).isEqualTo(amount);
    }

    @Test
    public void canSaveTopic(){

        IPersistenceAdapter pa = new PersistenceAdapter();
    }
}
