package hwr.oop.cards;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BoxTests {
    @Nested
    class canAddCards{
        @Test
        void canAddOneCardToBox(){
            Box box = new Box();
            Card card = new Card("What animal has the longest lifespan?", "The Greenland Shark (up to 400 years)");

            box.addCard(card);
            ArrayList<Card> cardList = box.getCards();

            Card firstCard = cardList.get(0);
            assertThat(card).isEqualTo(firstCard);
        }

        @Test
        void canAddMultipleCardsToBox(){
            Box box = new Box();
            Card card1 = new Card("What was the first item to be sold on Ebay?", "A broken laser pointer");
            Card card2 = new Card("What is the largest living organism on earth?", "The Great Barrier Reef");
            box.addCard(card1);
            box.addCard(card2);
            ArrayList<Card> cardList = box.getCards();

            boolean containsCard1 = false;
            boolean containsCard2 = false;

            if(cardList.contains(card1)) containsCard1 = true;
            if(cardList.contains(card2)) containsCard2 = true;

            assertThat(containsCard1).isEqualTo(true);
            assertThat(containsCard1).isEqualTo(true);
        }

        @Test
        void canDrawRandomCardFromBox(){

            Box box = new Box();

            Card card1 = new Card("What is the smallest mammal in the world?", "The bumblebee bat.");
            Card card2 = new Card("What is the highest-grossing movie of all time?", "Avatar, which grossed over $2.7 billion worldwide.");
            Card card3 = new Card("What is the longest word in the English language?", "Pneumonoultramicroscopicsilicovolcanoconiosis");

            box.addCard(card1);
            box.addCard(card2);
            box.addCard(card3);

            Card testCard;

            for (int i = 0; i < 3; i++){
                testCard = box.draw();

                if (!testCard.equals(card1) && !testCard.equals(card2) && !testCard.equals(card3)){
                    fail("None of the drawn cards were the expected card.");
                }
            }
        }
    }
}
