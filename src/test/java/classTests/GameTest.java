package classTests;

import classes.Board;
import classes.Game;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    void testNewBoard()
    {
        Game game = new Game();
        Board board = game.newBoard();
        Assertions.assertThat(board).isNotNull();
    }
}
