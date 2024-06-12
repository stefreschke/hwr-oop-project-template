package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import hwr.oop.doppelkopf.group6.cli.CreateCommand;
import hwr.oop.doppelkopf.group6.cli.IOExceptionBomb;
import hwr.oop.doppelkopf.group6.cli.InitCommand;
import hwr.oop.doppelkopf.group6.cli.ParseCommand;
import hwr.oop.doppelkopf.group6.persistence.SaveToFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaveToFileTest {
    private OutputStream outputStream;
    CreateCommand command;
    String fileName = "doppelkopf.csv";
    Path currentRelativePath = Paths.get("");
    String currentDir = currentRelativePath.toAbsolutePath().toString();
    File file = new File(currentDir + File.separator + fileName);
    Path path = file.toPath();

    @BeforeEach
    void setUp() throws IOException {
        outputStream = new ByteArrayOutputStream();
        command = new CreateCommand(outputStream, IOExceptionBomb.DONT);

        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @AfterEach
    void tearDown() throws IOException {

        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
    //Save to File Klasse Tests
    @Test
    void testSavePlayersToFile() throws IOException {
        CreateCommand create = new CreateCommand(outputStream, IOExceptionBomb.DONT);
        List<String> args = List.of("game", "2", "create", "josef", "anna", "jannis", "lena");

        create.execute(args);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean gameIDFound = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("2,josef")
                        && line.contains("anna")
                        && line.contains("jannis")
                        && line.contains("lena")) {
                    gameIDFound = true;
                    break;
                }
            }
            assertThat(gameIDFound).isTrue();
        }
    }

    @Test
    void testFormatPlayerCardsReturn() {
        SaveToFile saveToFile = new SaveToFile();

        String input = "[Card1, Card2, Card3]";
        String expectedOutput = "Card1, Card2, Card3";
        assertEquals(expectedOutput, saveToFile.formatPlayerCards(input));

        String inputNoBrackets = "Card1, Card2, Card3";
        assertEquals(inputNoBrackets, saveToFile.formatPlayerCards(inputNoBrackets));

        String emptyInput = "[]";
        String expectedEmptyOutput = "";
        assertEquals(expectedEmptyOutput, saveToFile.formatPlayerCards(emptyInput));

        String emptyCardsInput = "[ ]";
        String expectedEmptyCardsOutput = " ";
        assertEquals(expectedEmptyCardsOutput, saveToFile.formatPlayerCards(emptyCardsInput));
    }

    @Test
    void testUpdateLine_ContainsPlayerName() {
        SaveToFile saveToFile = new SaveToFile();
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        when(player1.getName()).thenReturn("Lisa");
        when(player2.getName()).thenReturn("Bob");

        List<Card> cardsPlayer1 = Arrays.asList(
                new Card(Color.PIK, Type.ASS, Group.TRUMPF, Type.ASS.getShortcut() + Color.PIK.getShortcut()),
                new Card(Color.HERZ, Type.KOENIG, Group.TRUMPF, Type.KOENIG.getShortcut() + Color.HERZ.getShortcut())
        );
        List<Card> cardsPlayer2 = Arrays.asList(
                new Card(Color.KARO, Type.DAME, Group.TRUMPF, Type.DAME.getShortcut() + Color.KARO.getShortcut()),
                new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, Type.BUBE.getShortcut() + Color.KREUZ.getShortcut())
        );

        when(player1.getOwnCards()).thenReturn(cardsPlayer1);
        when(player2.getOwnCards()).thenReturn(cardsPlayer2);

        List<Player> players = Arrays.asList(player1, player2);
        String line = "1,Lisa,Bob";

        String updatedLine = saveToFile.updateLine(line, players);

        assertThat(updatedLine).contains("1,"+"Lisa,"+cardsPlayer1.getFirst()+", "+cardsPlayer1.get(1)+","+"Bob,"+cardsPlayer2.getFirst()+", "+cardsPlayer2.get(1));
    }

    @Test
    void testUpdateLine_DoesNotContainPlayerName() {
        SaveToFile saveToFile = new SaveToFile();
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        when(player1.getName()).thenReturn("Lisa");
        when(player2.getName()).thenReturn("Bob");

        List<Card> cardsPlayer1 = Arrays.asList(
                new Card(Color.PIK, Type.ASS, Group.PIK, Type.ASS.getShortcut() + Color.PIK.getShortcut()),
                new Card(Color.HERZ, Type.KOENIG, Group.HERZ, Type.KOENIG.getShortcut() + Color.HERZ.getShortcut())
        );
        List<Card> cardsPlayer2 = Arrays.asList(
                new Card(Color.KARO, Type.DAME, Group.TRUMPF, Type.DAME.getShortcut() + Color.KARO.getShortcut()),
                new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, Type.BUBE.getShortcut() + Color.KREUZ.getShortcut())
        );

        when(player1.getOwnCards()).thenReturn(cardsPlayer1);
        when(player2.getOwnCards()).thenReturn(cardsPlayer2);

        List<Player> players = Arrays.asList(player1, player2);
        String line = "1,Charlie";

        String updatedLine = saveToFile.updateLine(line, players);

        SoftAssertions.assertSoftly(
                softly -> {
                    softly.assertThat(updatedLine).doesNotContain("Lisa");
                    softly.assertThat(updatedLine).doesNotContain("Bob");
                    softly.assertThat(updatedLine).isEqualTo(line);
                });
    }

    @Test
    void testUpdateFileContent_GameIDMatches() {
        List<Card> cardsPlayer1 = Arrays.asList(
                new Card(Color.PIK, Type.ASS, Group.PIK, Type.ASS.getShortcut() + Color.PIK.getShortcut()),
                new Card(Color.HERZ, Type.KOENIG, Group.HERZ, Type.KOENIG.getShortcut() + Color.HERZ.getShortcut())
        );
        List<Card> cardsPlayer2 = Arrays.asList(
                new Card(Color.KARO, Type.DAME, Group.TRUMPF, Type.DAME.getShortcut() + Color.KARO.getShortcut()),
                new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, Type.BUBE.getShortcut() + Color.KREUZ.getShortcut())
        );

        SaveToFile saveToFile = new SaveToFile();
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        when(player1.getName()).thenReturn("Lisa");
        when(player2.getName()).thenReturn("Bob");
        when(player1.getOwnCards()).thenReturn(cardsPlayer1);
        when(player2.getOwnCards()).thenReturn(cardsPlayer2);

        List<Player> players = Arrays.asList(player1, player2);
        String gameID = "123";

        List<String> fileContent = Arrays.asList(
                "123,Lisa",
                "456,Bob"
        );

        List<String> updatedContent = saveToFile.updateFileContent(fileContent, players, gameID);

        assertThat(updatedContent.getFirst()).contains("123,Lisa,"+cardsPlayer1.getFirst()+", "+cardsPlayer1.get(1));
        assertThat(updatedContent.getFirst()).contains("123");

        assertThat(updatedContent.get(1)).isEqualTo("456,Bob");
    }

    @Test
    void testUpdateFileContent_GameIDDoesNotMatch() {
        SaveToFile saveToFile = new SaveToFile();
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        when(player1.getName()).thenReturn("Lisa");
        when(player2.getName()).thenReturn("Bob");

        List<Player> players = Arrays.asList(player1, player2);
        String gameID = "789";

        List<String> fileContent = Arrays.asList(
                "123,Lisa",
                "456,Bob"
        );

        List<String> updatedContent = saveToFile.updateFileContent(fileContent, players, gameID);

        assertThat(updatedContent.get(0)).isEqualTo("123,Lisa");
        assertThat(updatedContent.get(1)).isEqualTo("456,Bob");
    }

    @Test
    void testWriteFile() throws IOException {
        List<Card> cardsPlayer1 = Arrays.asList(
                new Card(Color.PIK, Type.ASS, Group.PIK, Type.ASS.getShortcut() + Color.PIK.getShortcut()),
                new Card(Color.HERZ, Type.KOENIG, Group.HERZ, Type.KOENIG.getShortcut() + Color.HERZ.getShortcut())
        );
        List<Card> cardsPlayer2 = Arrays.asList(
                new Card(Color.KARO, Type.DAME, Group.TRUMPF, Type.DAME.getShortcut() + Color.KARO.getShortcut()),
                new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, Type.BUBE.getShortcut() + Color.KREUZ.getShortcut())
        );
        SaveToFile save = new SaveToFile();
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);

        when(player1.getName()).thenReturn("Lisa");
        when(player2.getName()).thenReturn("Bob");
        when(player1.getOwnCards()).thenReturn(cardsPlayer1);
        when(player2.getOwnCards()).thenReturn(cardsPlayer2);

        List<Player> players = Arrays.asList(player1, player2);
        String gameID = "123";

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("123,Lisa");
            writer.newLine();
            writer.write("456,Bob");
            writer.newLine();
        }

        save.cards(players, gameID);

        List<String> lines = Files.readAllLines(path);

        assertThat(lines).hasSize(2);
        assertThat(lines.get(0)).contains("123,Lisa,"+cardsPlayer1.getFirst()+", "+cardsPlayer1.get(1));
        assertThat(lines.get(0)).contains("123");
        assertThat(lines.get(1)).isEqualTo("456,Bob");
    }
}
