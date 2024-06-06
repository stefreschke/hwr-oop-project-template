package hwr.oop.most_impressive_doppelkopf_experience;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardColours;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatePlayersTest {

    public List<Player> createPlayers() {
        Player player1 = new Player("Colin", 0, 0);
        Player player2 = new Player("Chrissi", 0, 1);
        Player player3 = new Player("Mihoshi", 0, 2);
        Player player4 = new Player("Josh", 0, 3);
        return List.of(player1, player2, player3, player4);
    }
    @Test
    void testCreatePlayers() {
        List<Player> players = createPlayers();
        assertThat(players).hasSize(4);
    }
    @Test
    void testsetName() {
        Player player = new Player("John", 0, 1);
        player.setName("Jane");
        assertEquals("Jane", player.getName());
    }
    @Test
    void testgetPoints() {
        Player player = new Player("John", 0, 1);
        assertEquals(0, player.getPoints());
    }
    @Test
    void testsetPoint() {
        Player player = new Player("John", 0, 1);
        player.setPoint(100);
        assertEquals(100, player.getPoints());
    }
    @Test
    void testsetScore() {
        Player player = new Player("John", 0, 1);
        player.setScore(50);
        assertEquals(50, player.getScore());
    }
    @Test
    void testsetCardsWon() {
        Player player = new Player("John", 0, 1);
        List<Card> cardsWon = new ArrayList<>();
        cardsWon.add(new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10));
        player.setCardsWon(cardsWon);
        assertEquals(cardsWon, player.getCardsWon());
    }
    @Test
    void testcalculateScore() {
        Player player = new Player("John", 0, 1);
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10));
        cards.add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 19, "HQ", 3));
        assertEquals(13, player.calculateScore(cards));
    }
    @Test
    void testplayerHasWonStich() {
        Player player = new Player("John", 0, 1);
        List<Card> stich = new ArrayList<>();
        stich.add(new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10));
        stich.add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 19, "HQ", 3));
        player.playerHasWonStich(stich);
        assertEquals(13, player.getScore());
        assertEquals(stich, player.getCardsWon());
    }
}
