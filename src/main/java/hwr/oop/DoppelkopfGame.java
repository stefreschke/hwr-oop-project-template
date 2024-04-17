package hwr.oop;
import java.util.ArrayList;
import java.util.List;

public class DoppelkopfGame {
    public DoppelkopfGame() {
        initializeCards();
    }

    public List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();

        for (int k = 0; k < 2; k++) {
            for (Color i : Color.values()) {
                for (Type j : Type.values()) {
                    Card newCard = new Card(i, j);
                    cards.add(newCard);
                }
            }
        }
        return cards;
    }

    public boolean hasCard(List<Card> cards, Color color, Type number) {
        for(Card i : cards){
            if (i.color == color && i.number == number) {
                return true;
            }
        }
        return false;
    }
}
