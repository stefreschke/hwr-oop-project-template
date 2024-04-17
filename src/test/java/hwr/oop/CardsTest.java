package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CardsTest {
    @Test
    void testAllCards() {
        DoppelkopfGame game = new DoppelkopfGame();
        List<Card> cards = List.of();
        Assertions.assertThat(game.hasCard(cards, Color.PIK, Type.ASS)).isFalse();
        cards = game.initializeCards();

        for (Color color : Color.values()) {
            for (Type value : Type.values()) {
                assertTrue(game.hasCard(cards, color, value), "Karte " + color + " " + value + " fehlt im Spiel.");
            }
        }
    }

    @Test
    void testCountCards(){
        DoppelkopfGame game = new DoppelkopfGame();
        List<Card> cards = game.initializeCards();

        Assertions.assertThat(cards.size()).isEqualTo(48);
    }
}
