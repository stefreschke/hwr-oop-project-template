package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class NewBoxTest {
    @Test
    public void canContainCards(){
        BoxInterface box = new FirstBox(1);
        Card card = new Card(" Bob", "der Baumeeister", 1);
        box.addCard(card);
        Assertions.assertThat(box.isEmpty_learned()).isFalse();
    }
    @Test
    void canCreateFirstBox(){
        BoxInterface firstBox = new FirstBox(1);
        BoxInterface normalBox = new NormalBox(3, firstBox);
        BoxInterface lastBox = new LastBox(7, normalBox);
        Assertions.assertThat(firstBox.getNextBox()).isNotNull();
    }

    @Test
    void unlearnedCardListIsEmpty(){
        BoxInterface box = new FirstBox(1);
        boolean isEmpty = box.isEmpty_unlearned();
        assertThat(isEmpty).isTrue();
    }@Test
    void unlearnedCardListIsNotEmpty(){
        Card card = new Card("Test", "Frage", 0);
        BoxInterface box = new FirstBox(0);
        box.addCard(card); //landet in learned
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        box.updateBox();
        boolean isEmpty = box.isEmpty_unlearned();
        assertThat(isEmpty).isFalse();
    }@Test
    void learnedCardListIsEmpty(){
        BoxInterface box = new FirstBox(1);
        boolean isEmpty = box.isEmpty_learned();
        assertThat(isEmpty).isTrue();
    }@Test
    void learnedCardListIsNotEmpty(){
        Card card = new Card("Test", "Frage", 0);
        BoxInterface box = new FirstBox(1);
        box.addCard(card);
        boolean isEmpty = box.isEmpty_learned();
        assertThat(isEmpty).isFalse();
    }
    @Test //nicht iterieren und removen!!!!
    void canUpdateBox(){
        Card card = new Card("Test", "Frage", 0);
        BoxInterface box = new FirstBox(0);
        box.addCard(card); //landet in learned#
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        box.updateBox();
        boolean isEmpty = box.isEmpty_unlearned();
        assertThat(box.isEmpty_learned()).isTrue();
        assertThat(isEmpty).isFalse();
    }
    @Test
    void boxIsEmptyAfterDrawingAllCards(){
        BoxInterface box = new FirstBox(1);
        Card card1 = new Card("What is the smallest mammal in the world?", "The bumblebee bat.", 0);
        Card card2 = new Card("What is the highest-grossing movie of all time?", "Avatar, which grossed over $2.7 billion worldwide.", 1);
        Card card3 = new Card("What is the longest word in the English language?", "Pneumonoultramicroscopicsilicovolcanoconiosis", 2);

        box.addCard(card1);
        box.addCard(card2);
        box.addCard(card3);

        box.getRandomCard();
        box.getRandomCard();
        box.getRandomCard();

        assertThat(box.isEmpty());
    }
}
