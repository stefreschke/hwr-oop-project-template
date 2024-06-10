package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GamePersistanceTest {
  @Test
  void persistanceTest() {
    //Der Bums muss in einen Test!!!!!!!!!!!!!
    Game game = new Game();
    GamePersistence gamePersistence = new GamePersistence();

    game.addPlayer("Josh");
    game.addPlayer("Josh2");
    game.addPlayer("Josh3");
    game.addPlayer("Josh4");

    game.setStartPlayer(game.getPlayers().getFirst());
    game.handOutCards();

    gamePersistence.saveGame(game,"savedGame.ser");

    Game loadedGame = gamePersistence.loadGame("savedGame.ser");

    assertThat(loadedGame.getPlayers()).hasSameSizeAs(game.getPlayers());
  }
}
