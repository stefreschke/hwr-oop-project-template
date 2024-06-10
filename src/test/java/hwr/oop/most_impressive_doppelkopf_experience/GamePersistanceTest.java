package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamePersistanceTest {
  @Test
  void persistanceTest() {
    //Der Bums muss in einen Test!!!!!!!!!!!!!
    Game game = new Game();
    GamePersistence gamePersistence = new GamePersistence();

    game.addPlayer("Josh");
    game.addPlayer("Josh2");
    game.addPlayer("Josh3");
    game.addPlayer("Josh4");

    game.setStartPlayer(game.players.getFirst());
    game.handOutCards();

    gamePersistence.saveGame(game,"savedGame.ser");

    Game loadedGame = gamePersistence.loadGame("savedGame.ser");

    assertThat(loadedGame.players.size()).isEqualTo(game.players.size());
  }
}
