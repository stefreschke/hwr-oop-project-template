package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NewTopicLoadTest {
    @Test
    void cannotLoadTopicTwiceWithOutDuplication(){

    }
    @Test
    void canLoadTopic_OneCard(){
        Lernsession lernsession = Lernsession.createLernsessionWith3Boxes();
        Topic topic = new Topic("Spanish");
        topic.createCard("Marco", "Polo");
        lernsession.loadTopic(topic);
        Card cardRef1 = topic.getCardList().get(0);
        Card cardRef2 = lernsession.getBoxes().retrieve(0).get().getRandomCard();
        Assertions.assertThat(cardRef1).isEqualTo(cardRef2);
    }
    @Test
    void canLoadTopic_ThreeCards(){
        Lernsession lernsession = Lernsession.createLernsessionWith3Boxes();
        Topic topic = new Topic("Spanish");
        topic.createCard("Marco", "Polo");
        topic.createCard("Julius", "Caesar");
        topic.createCard("Freddy", "Faulig");
        lernsession.loadTopic(topic);
        Card cardRef1_topic = topic.getCardList().get(0);
        Card card1_rand = lernsession.getBoxes().retrieve(0).get().getRandomCard();
        Assertions.assertThat(topic.getCardList()).contains(card1_rand);
        Card cardRef2_topic = topic.getCardList().get(1);
        Card card2_rand = lernsession.getBoxes().retrieve(0).get().getRandomCard();
        Assertions.assertThat(topic.getCardList()).contains(card2_rand);
        Card cardRef3_topic = topic.getCardList().get(2);
        Card card3_rand = lernsession.getBoxes().retrieve(0).get().getRandomCard();
        Assertions.assertThat(topic.getCardList()).contains(card3_rand);
    }
}
