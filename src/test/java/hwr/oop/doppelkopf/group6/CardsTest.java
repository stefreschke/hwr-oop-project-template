package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class CardsTest {
  @Test
  void testAllCards() {
    Deck deck = new Deck();
    Deck deck2 = new Deck();
    deck.initializeCards();

    SoftAssertions.assertSoftly(
        softly -> {
          for (Color color : Color.values()) {
            for (Type value : Type.values()) {
              softly.assertThat(deck.hasCard(color, value)).isTrue();
              softly.assertThat(deck2.hasCard(color, value)).isFalse();
            }
          }
        });
  }

  @Test
  void testCardForInitialize() {
    Deck deck = new Deck();
    deck.initializeCards();
    SoftAssertions.assertSoftly(
        softly -> {
          for (Color color : Color.values()) {
            for (Type value : Type.values()) {
              softly.assertThat(deck.hasCard(color, value)).isTrue();
            }
          }

          for (Card i : deck.getCards()) {
            if (i.getColor() == Color.KARO
                || i.getNumber() == Type.BUBE
                || i.getNumber() == Type.DAME
                || (i.getColor() == Color.HERZ && i.getNumber() == Type.ZEHN)) {
              softly.assertThat(i.getGroup().equals(Group.TRUMPF)).isTrue();
            }
          }
        });
  }

  @Test
  void testAddCardAndTestColor() {
    Deck deck = new Deck();
    deck.addCard(new Card(Color.KREUZ, Type.NEUN, Group.KREUZ, "Kr9"));

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(deck.hasCard(Color.KREUZ, Type.NEUN)).isTrue();
          softly.assertThat(deck.hasCard(Color.KARO, Type.NEUN)).isFalse();
          softly.assertThat(deck.hasCard(Color.KREUZ, Type.ZEHN)).isFalse();
        });
  }

  @Test
  void testCountCards() {
    Deck deck = new Deck();
    deck.initializeCards();

    assertThat(deck.getCards()).hasSize(48);
  }

  @Test
  void testShuffle() {
    Deck shuffledDeck = new Deck();
    shuffledDeck.initializeCards();
    List<Card> oldDeck = List.copyOf(shuffledDeck.getCards());
    shuffledDeck.shuffleDeck();

    assertThat(shuffledDeck.getCards()).isNotEmpty().doesNotContainSequence(oldDeck);
  }

  @Test
  void testShortcut() {
    List<Card> cards =
        Arrays.asList(
            new Card(Color.HERZ, Type.BUBE, Group.TRUMPF, "HB"),
            new Card(Color.KREUZ, Type.NEUN, Group.KREUZ, "KR9"),
            new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"));
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

    players.getFirst().getHand().addCard(new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"));
    players.getFirst().getHand().addCard(new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"));
    players.getFirst().getHand().addCard(new Card(Color.PIK, Type.BUBE, Group.TRUMPF, "PB"));
    players.getFirst().getHand().addCard(new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    players.getFirst().getHand().addCard(new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"));

    players.get(1).getHand().addCard(new Card(Color.KREUZ, Type.KOENIG, Group.KREUZ, "KrK"));
    players.get(1).getHand().addCard(new Card(Color.KARO, Type.NEUN, Group.TRUMPF, "Ka9"));
    players.get(1).getHand().addCard(new Card(Color.HERZ, Type.BUBE, Group.TRUMPF, "HB"));
    players.get(1).getHand().addCard(new Card(Color.KARO, Type.KOENIG, Group.TRUMPF, "KaK"));
    players.get(1).getHand().addCard(new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"));

    List<Card> richCards = new ArrayList<>();
    richCards.add(new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"));
    richCards.add(new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"));
    richCards.add(new Card(Color.PIK, Type.BUBE, Group.TRUMPF, "PB"));

    List<Card> poorCards = new ArrayList<>();
    poorCards.add(new Card(Color.KREUZ, Type.KOENIG, Group.KREUZ, "KrK"));
    poorCards.add(new Card(Color.HERZ, Type.BUBE, Group.TRUMPF, "HB"));
    poorCards.add(new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"));

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
    poorPlayer.getHand().addCard(new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"));
    poorPlayer.getHand().addCard(new Card(Color.KARO, Type.NEUN, Group.TRUMPF, "Ka9"));
    poorPlayer.getHand().addCard(new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    poorPlayer.getHand().addCard(new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"));

    DoppelkopfGame game = new DoppelkopfGame();
    boolean resultPoor = game.checkForPoverty(poorPlayers);

    Player richPlayer = new Player("richPlayer", 1, 0);
    List<Player> richPlayers = new ArrayList<>();
    richPlayers.add(richPlayer);
    richPlayer.getHand().addCard(new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"));
    richPlayer.getHand().addCard(new Card(Color.KARO, Type.NEUN, Group.TRUMPF, "Ka9"));
    richPlayer.getHand().addCard(new Card(Color.HERZ, Type.BUBE, Group.TRUMPF, "HB"));
    richPlayer.getHand().addCard(new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, "KrB"));

    boolean resultRich = game.checkForPoverty(richPlayers);

    Player playerExact = new Player("exactThreeTrumpPlayer", 1, 0);
    List<Player> exactPlayers = new ArrayList<>();
    exactPlayers.add(playerExact);
    playerExact.getHand().addCard(new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"));
    playerExact.getHand().addCard(new Card(Color.KARO, Type.NEUN, Group.TRUMPF, "Ka9"));
    playerExact.getHand().addCard(new Card(Color.HERZ, Type.BUBE, Group.TRUMPF, "HB"));

    boolean resultExact = game.checkForPoverty(exactPlayers);

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(resultPoor).isTrue();
          softly.assertThat(resultRich).isFalse();
          softly.assertThat(resultExact).isTrue();
        });
  }
}
