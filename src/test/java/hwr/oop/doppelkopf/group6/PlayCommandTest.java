package hwr.oop.doppelkopf.group6;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import hwr.oop.doppelkopf.group6.cli.IOExceptionBomb;
import hwr.oop.doppelkopf.group6.cli.ParseCommand;
import hwr.oop.doppelkopf.group6.cli.PlayCommand;
import hwr.oop.doppelkopf.group6.persistenz.SaveToFile;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class PlayCommandTest {
    private SaveToFile save;
    private PlayCommand playCommand;
    private ParseCommand parse;

    @BeforeEach
    void setUp() {
        Deck deck = mock(Deck.class);
        save = mock(SaveToFile.class);
        parse = mock(ParseCommand.class);
        playCommand = new PlayCommand(IOExceptionBomb.DONT, deck, save, parse);
    }

    @Test
    void testExecuteCallsSaveCards() throws Exception {
        List<String> args = List.of("gameID:123", "players:Alice,Bob,Charlie");
        List<Player> players = List.of(new Player("Alice", 1, 0), new Player("Bob", 2, 0), new Player("Charlie", 3, 0));

        when(parse.gameID(args)).thenReturn("123");
        when(parse.players(args)).thenReturn(players);

        playCommand.execute(args);

        ArgumentCaptor<List<Player>> playersCaptor = ArgumentCaptor.forClass((Class)List.class);
        ArgumentCaptor<String> gameIDCaptor = ArgumentCaptor.forClass(String.class);

        verify(save).cards(playersCaptor.capture(), gameIDCaptor.capture());

        assertEquals(players, playersCaptor.getValue());
        assertEquals("123", gameIDCaptor.getValue());
    }
}

