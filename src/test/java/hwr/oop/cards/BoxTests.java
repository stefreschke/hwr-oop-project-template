package hwr.oop.cards;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class BoxTests {
    @Test
    void canDrawRandomCardFromBox(){

        Box box = new Box();

        Card card1 = new Card("What is the smallest mammal in the world?", "The bumblebee bat.");
        Card card2 = new Card("What is the highest-grossing movie of all time?", "Avatar, which grossed over $2.7 billion worldwide.");
        Card card3 = new Card("What is the longest word in the English language?", "Pneumonoultramicroscopicsilicovolcanoconiosis");

        box.addCard(card1);
        box.addCard(card2);
        box.addCard(card3);

        Card testCard = box.draw();

        if (!testCard.equals(card1) && !testCard.equals(card2) && !testCard.equals(card3)) {
            fail("The drawn card was not one of the expected cards.");
        }
    }
}
