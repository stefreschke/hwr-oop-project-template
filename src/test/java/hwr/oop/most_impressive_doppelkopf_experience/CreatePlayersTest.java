package hwr.oop.most_impressive_doppelkopf_experience;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatePlayersTest {

    public List<Player> createPlayers() {
        Player player1 = new Player("Colin", 0, 0);
        Player player2 = new Player("Chrissi", 0, 1);
        Player player3 = new Player("Mihoshi", 0, 2);
        Player player4 = new Player("Josh", 0, 3);
        return List.of(player1, player2, player3, player4);
    }
    @Test
    public void testCreatePlayers() {
        List<Player> players = createPlayers();
        assertThat(4, players.size());
    }

    private void assertThat(int i, int size) {
    }
}
