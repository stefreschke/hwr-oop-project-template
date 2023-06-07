package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class NewGetCardTest {
    private Lernsession lernsession;
    private Topic topic;
    private NewBox box1;
    private NewBox box2;
    private NewBox box3;
    private Card compareCard;
    @BeforeEach
    void setup(){
        lernsession = Lernsession.createLernsessionWith3Boxes();
        topic = new Topic("Random");
        box1 = lernsession.getBoxes().retrieve(0).get();
        box2 = lernsession.getBoxes().retrieve(1).get();
        box3 = lernsession.getBoxes().retrieve(2).get();
        topic.createCard("Penny", "Doku");
        topic.createCard("escalera", "Leiter");
        topic.createCard("caballo", "Pferd");
        lernsession.loadTopic(topic);
    }
    @Test
    void canGetRandomBoxIndex(){
        int randomBoxIndex = lernsession.getRandomBoxIndex();
        Assertions.assertThat(randomBoxIndex).isIn(List.of(0,1,2));
    }
    @Test
    void canGetRandomBoxIndexFromList(){
        int randomBoxIndex = lernsession.getRandomBoxIndexFromList(List.of(0,1,2));
        Assertions.assertThat(randomBoxIndex).isIn(List.of(0,1,2));
    }
    @Test
    void canGetRandomCardFromRandomBox(){
        Card card = lernsession.getRandomCard();
        Assertions.assertThat(card).isIn(topic.getCardList());
    }
    @Test
    public void cannotReturnRandomCardFromEmptyLernsession(){
        Lernsession lernsession1 = Lernsession.createLernsessionWith3Boxes();
        assertThrows(Lernsession.EmptyBoxesException.class, () -> lernsession1.getRandomCard());
    }

    @Test
    public void canReturnRandomCard(){

        Boxes mediator = Boxes.createBoxes(3);
        NewBox box = mediator.retrieve(0).get();
        Card card1 = new Card("What is the smallest mammal in the world?", "The bumblebee bat.", 0);
        Card card2 = new Card("What is the highest-grossing movie of all time?", "Avatar, which grossed over $2.7 billion worldwide.", 1);
        Card card3 = new Card("What is the longest word in the English language?", "Pneumonoultramicroscopicsilicovolcanoconiosis", 2);

        box.addCard(card1);
        box.addCard(card2);
        box.addCard(card3);

        Card testCard = box.getRandomCard();

        if (!testCard.equals(card1) && !testCard.equals(card2) && !testCard.equals(card3)){
            fail("Drawn card does not equal one of the test cards.");
        }
    }

}