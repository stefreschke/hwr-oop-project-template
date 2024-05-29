package hwr.oop.most_impressive_doppelkopf_experience;
import org.junit.jupiter.api.Test;
import java.util.List;

import static hwr.oop.most_impressive_doppelkopf_experience.Game.createPlayers;
import static org.assertj.core.api.Assertions.assertThat;
class CreatePlayersTest {
    @Test
    void testCreatePlayers() {
        List<Player> players = createPlayers();
        assertThat(players).hasSize(4);
    }}
