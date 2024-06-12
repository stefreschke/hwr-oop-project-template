package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class HandTest {
  @Test
  void testRemoveCardWithCard() {
    Hand hand = new Hand();
    hand.addCard(
        new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
        new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    hand.removeCard("KrA");

    assertThat(hand.getAllCards()).hasSize(1);
  }

  @Test
  void testRemoveCardWithNOtExisistingCard() {
    Hand hand = new Hand();
    hand.addCard(
        new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
        new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    hand.removeCard("Kr9");

    assertThat(hand.getAllCards()).hasSize(2);
  }

  @Test
  void testPlayFirstCardWithShortcut() {
    Hand hand = new Hand();
    Stich stich = new Stich();
    hand.addCard(
        new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
        new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    hand.playFirstCard("HK", stich);

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(hand.getAllCards()).hasSize(1);
          softly.assertThat(stich.getCards()).hasSize(1);
        });
  }

  @Test
  void testPlayFirstCardWithShortcutNotExisting() {
    Hand hand = new Hand();
    Stich stich = new Stich();
    hand.addCard(
        new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
        new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    hand.playFirstCard("H9", stich);

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(hand.getAllCards()).hasSize(2);
          softly.assertThat(stich.getCards()).hasSize(0);
        });
  }

  @Test
  void testPlayCardWithShortcut() {
    Hand hand = new Hand();
    Stich stich = new Stich();
    hand.addCard(
        new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
        new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    hand.playFirstCard("HK", stich);
    hand.playCard("KrA", stich);

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(hand.getAllCards()).hasSize(0);
          softly.assertThat(stich.getCards()).hasSize(2);
        });
  }

  @Test
  void testPlayCardWithShortcutNotExisting() {
    Hand hand = new Hand();
    Stich stich = new Stich();
    hand.addCard(
        new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
        new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    hand.playFirstCard("HK", stich);
    hand.playCard("KrB", stich);

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(hand.getAllCards()).hasSize(1);
          softly.assertThat(stich.getCards()).hasSize(1);
        });
  }
}
