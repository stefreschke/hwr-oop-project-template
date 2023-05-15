package hwr.oop.cards;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Trainer {
    private final List<Box> boxList;

    public Trainer() {
        boxList = List.of(new Box(), new Box(), new Box());
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    // loading new Topic
    void loadTopic(Topic topic) {
        ArrayList<Card> cardList = topic.getCardList();
        for (Card card : cardList) {
            boxList.get(0).addCard(card);
        }
    }

    /* Da man nach dem removen einer Card aus einer Box nicht weiß,
    woher sie kommmt, würde ich die boxNummer als int passen.
    Somit kann man z.b. beim Checken der Antwort dann die boxNummer
    in- bzw. dekrementieren je nachdem, ob die Anwort richtig oder falsch war
     */
    void moveCardIntoBox(Card card, int boxNumber) {
        getBoxList().get(boxNumber).addCard(card);
    }
}
