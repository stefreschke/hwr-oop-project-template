package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class CardsTest {
  @Test
  void testAllCards() {
    DoppelkopfGame game = new DoppelkopfGame();
    List<Card> cards = game.initializeCards();

    for (Color color : Color.values()) {
      for (Type value : Type.values()) {
        assertTrue(
            game.hasCard(cards, color, value), "Karte " + color + " " + value + " fehlt im Spiel.");
      }
    }
  }

  @Test
  void testCardForInitialize() {
    DoppelkopfGame game = new DoppelkopfGame();
    List<Card> cards = game.initializeCards();
    SoftAssertions.assertSoftly(
        softly -> {
          for (Color color : Color.values()) {
            for (Type value : Type.values()) {
              softly.assertThat(game.hasCard(cards, color, value)).isTrue();
            }
          }

          for (Card i : cards) {
            if (i.getColor() == Color.KARO
                || i.getNumber() == Type.BUBE
                || i.getNumber() == Type.DAME
                || (i.getColor() == Color.HERZ && i.getNumber() == Type.ZEHN)) {
              softly.assertThat(i.isTrump()).isTrue();
            }
          }
        });
  }

  @Test
  void testCountCards() {
    DoppelkopfGame game = new DoppelkopfGame();
    List<Card> cards = game.initializeCards();

    assertThat(cards).hasSize(48);
  }

  @Test
  void testShuffle() {
    DoppelkopfGame game = new DoppelkopfGame();

    List<Card> unshuffledCards = game.initializeCards();
    List<Card> shuffledCards = game.shuffleDeck(unshuffledCards);

    assertThat(shuffledCards).isNotEmpty().doesNotContainSequence(unshuffledCards);
  }

  @Test
  void testColor() {
    List<Card> cards =
        Arrays.asList(
            new Card(Color.HERZ, Type.BUBE, true, "HB"),
            new Card(Color.KREUZ, Type.NEUN, false, "KR9"),
            new Card(Color.PIK, Type.ZEHN, false, "P10"));
    boolean result1 = new DoppelkopfGame().hasCard(cards, Color.KARO, Type.KOENIG);
    boolean result2 = new DoppelkopfGame().hasCard(cards, Color.PIK, Type.ZEHN);

    SoftAssertions.assertSoftly(
        softly -> {
          assertThat(result1).isFalse();
          assertThat(result2).isTrue();
        });
  }

  @Test
  void testShortcut() {
    List<Card> cards =
        Arrays.asList(
            new Card(Color.HERZ, Type.BUBE, true, "HB"),
            new Card(Color.KREUZ, Type.NEUN, false, "KR9"),
            new Card(Color.PIK, Type.ZEHN, false, "P10"));
    String result = cards.getFirst().getShortcut();

    Color colorHerz = Color.HERZ;
    Color colorKaro = Color.KARO;
    Color colorKreuz = Color.KREUZ;
    Color colorPik = Color.PIK;

    Type typeBube = Type.BUBE;
    Type typeNeun = Type.NEUN;
    Type typeZehn = Type.ZEHN;
    Type typeAss = Type.ASS;
    Type typeDame = Type.DAME;
    Type typeKoenig = Type.KOENIG;

    SoftAssertions.assertSoftly(
        softly -> {
          //
          softly.assertThat(result).isEqualTo("HB");
          // Color shortcut test
          softly.assertThat(colorHerz.getShortcut()).isEqualTo("H");
          softly.assertThat(colorKaro.getShortcut()).isEqualTo("Ka");
          softly.assertThat(colorKreuz.getShortcut()).isEqualTo("Kr");
          softly.assertThat(colorPik.getShortcut()).isEqualTo("P");
          // Type shortcut test
          softly.assertThat(typeBube.getShortcut()).isEqualTo("B");
          softly.assertThat(typeNeun.getShortcut()).isEqualTo("9");
          softly.assertThat(typeZehn.getShortcut()).isEqualTo("10");
          softly.assertThat(typeAss.getShortcut()).isEqualTo("A");
          softly.assertThat(typeDame.getShortcut()).isEqualTo("D");
          softly.assertThat(typeKoenig.getShortcut()).isEqualTo("K");
        });
  }

  @Test
  void testCardPointsAndStrength() {
    Type typeBube = Type.BUBE;
    Type typeNeun = Type.NEUN;
    Type typeZehn = Type.ZEHN;
    Type typeAss = Type.ASS;
    Type typeDame = Type.DAME;
    Type typeKoenig = Type.KOENIG;

    SoftAssertions.assertSoftly(
        softly -> {
          // Card points
          softly.assertThat(typeBube.getPoints()).isEqualTo(2);
          softly.assertThat(typeNeun.getPoints()).isZero();
          softly.assertThat(typeZehn.getPoints()).isEqualTo(10);
          softly.assertThat(typeAss.getPoints()).isEqualTo(11);
          softly.assertThat(typeDame.getPoints()).isEqualTo(3);
          softly.assertThat(typeKoenig.getPoints()).isEqualTo(4);
          // Card strength
          softly.assertThat(typeNeun.getStrength()).isEqualTo(0);
          softly.assertThat(typeZehn.getStrength()).isEqualTo(2);
          softly.assertThat(typeKoenig.getStrength()).isEqualTo(1);
          softly.assertThat(typeAss.getStrength()).isEqualTo(3);
          softly.assertThat(typeBube.getStrength()).isEqualTo(4);
          softly.assertThat(typeDame.getStrength()).isEqualTo(5);
        });
  }

  @Test
  void testSwitchCardsPoverty() {
    List<Player> players = new ArrayList<>();
    players.add(new Player("richPlayer", 1, 0));
    players.add(new Player("poorPlayer", 2, 0));

    players.getFirst().addCard(new Card(Color.HERZ, Type.DAME, true, "HD"));
    players.getFirst().addCard(new Card(Color.PIK, Type.NEUN, false, "P9"));
    players.getFirst().addCard(new Card(Color.PIK, Type.BUBE, true, "PB"));
    players.getFirst().addCard(new Card(Color.HERZ, Type.KOENIG, false, "HK"));
    players.getFirst().addCard(new Card(Color.KREUZ, Type.ZEHN, false, "Kr10"));

    players.get(1).addCard(new Card(Color.KREUZ, Type.KOENIG, false, "KrK"));
    players.get(1).addCard(new Card(Color.KARO, Type.NEUN, true, "Ka9"));
    players.get(1).addCard(new Card(Color.HERZ, Type.BUBE, true, "HB"));
    players.get(1).addCard(new Card(Color.KARO, Type.KOENIG, false, "KaK"));
    players.get(1).addCard(new Card(Color.PIK, Type.ZEHN, false, "P10"));

    List<Card> richCards = new ArrayList<>();
    richCards.add(new Card(Color.HERZ, Type.DAME, true, "HD"));
    richCards.add(new Card(Color.PIK, Type.NEUN, false, "P9"));
    richCards.add(new Card(Color.PIK, Type.BUBE, true, "PB"));

    List<Card> poorCards = new ArrayList<>();
    poorCards.add(new Card(Color.KREUZ, Type.KOENIG, false, "KrK"));
    poorCards.add(new Card(Color.HERZ, Type.BUBE, true, "HB"));
    poorCards.add(new Card(Color.PIK, Type.ZEHN, false, "P10"));

    DoppelkopfGame game = new DoppelkopfGame();
    game.switchPlayerCardsDuringPoverty(poorCards, 1, richCards, 0);

    assertThat(players.getFirst().getOwnCards()).doesNotContain(richCards.getFirst());
    assertThat(players.get(1).getOwnCards()).doesNotContain(poorCards.getFirst());
  }

  @Test
  void testCheckPoverty() {
    Player poorPlayer = new Player("poorPlayer", 1, 0);
    List<Player> poorPlayers = new ArrayList<>();
    poorPlayers.add(poorPlayer);
    poorPlayer.addCard(new Card(Color.HERZ, Type.DAME, true, "HD"));
    poorPlayer.addCard(new Card(Color.KARO, Type.NEUN, true, "Ka9"));
    poorPlayer.addCard(new Card(Color.HERZ, Type.KOENIG, false, "HK"));
    poorPlayer.addCard(new Card(Color.KREUZ, Type.ZEHN, false, "Kr10"));

    DoppelkopfGame game = new DoppelkopfGame();
    boolean resultPoor = game.checkForPoverty(poorPlayers);

    Player richPlayer = new Player("richPlayer", 1, 0);
    List<Player> richPlayers = new ArrayList<>();
    richPlayers.add(richPlayer);
    richPlayer.addCard(new Card(Color.HERZ, Type.DAME, true, "HD"));
    richPlayer.addCard(new Card(Color.KARO, Type.NEUN, true, "Ka9"));
    richPlayer.addCard(new Card(Color.HERZ, Type.BUBE, true, "HB"));
    richPlayer.addCard(new Card(Color.KREUZ, Type.BUBE, true, "KrB"));

    boolean resultRich = game.checkForPoverty(richPlayers);

    Player playerExact = new Player("exactThreeTrumpPlayer", 1, 0);
    List<Player> exactPlayers = new ArrayList<>();
    exactPlayers.add(playerExact);
    playerExact.addCard(new Card(Color.HERZ, Type.DAME, true, "HD"));
    playerExact.addCard(new Card(Color.KARO, Type.NEUN, true, "Ka9"));
    playerExact.addCard(new Card(Color.HERZ, Type.BUBE, true, "HB"));

    boolean resultExact = game.checkForPoverty(exactPlayers);

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(resultPoor).isTrue();
          softly.assertThat(resultRich).isFalse();
          softly.assertThat(resultExact).isTrue();
        });
  }
}
