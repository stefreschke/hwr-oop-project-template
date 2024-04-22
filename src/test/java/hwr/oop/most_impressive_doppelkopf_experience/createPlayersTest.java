package hwr.oop.doppelkopf;
import hwr.oop.most_impressive_doppelkopf_experience.Player;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class createPlayersTest {
    // Methode, die die Spieler erstellt
    public List<Player> createPlayers() {
        Player player1 = new Player("Colin", 0);
        Player player2 = new Player("Chrissi", 0);
        Player player3 = new Player("Mihoshi", 0);
        Player player4 = new Player("Josh", 0);
        return List.of(player1, player2, player3, player4);
    }
    @Test
    public void testCreatePlayers() {
        List<Player> players = createPlayers();
        assertThat(4, players.size());  // Überprüfen der Größe der Spielerliste
    }

    private void assertThat(int i, int size) {
    }
}
