package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    CreateRandomDeck createRandomDeck = new CreateRandomDeck();
    DoppelkopfGame game = new DoppelkopfGame();

    List<Card> unshuffledCards = game.initializeCards();
    List<Card> shuffledCards = createRandomDeck.shuffleDeck(unshuffledCards);

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
}
