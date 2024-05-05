package hwr.oop.doppelkopf.group6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

class DoppelkopfGameTest {
    @Test
    void oneRoundTest(){
        DoppelkopfGame game = new DoppelkopfGame();
        CreateRandomDeck deck = new CreateRandomDeck();
        game.dealCards(deck.shuffleDeck(game.initializeCards()));
        int winner = game.oneRound();
        assertThat(winner).isBetween(1,4);
    }
    @Test
    void findHighestCardTest(){
        DoppelkopfGame game = new DoppelkopfGame();
        CreateRandomDeck deck = new CreateRandomDeck();
        game.dealCards(deck.shuffleDeck(game.initializeCards()));
        List<Card> testList1 = new ArrayList<>();
        List<Card> testList2 = new ArrayList<>();
        List<Card> testList3 = new ArrayList<>();
        List<Card> testList4 = new ArrayList<>();
        List<Card> testList5 = new ArrayList<>();
        testList1.add(new Card (Color.HERZ, Type.ASS, false));
        testList1.add(new Card (Color.HERZ, Type.BUBE, true));
        testList1.add(new Card (Color.PIK, Type.ASS, true));
        testList2.add(new Card (Color.KREUZ, Type.ZEHN, false));
        testList3.add(new Card (Color.PIK, Type.ZEHN, false));
        testList3.add(new Card (Color.PIK, Type.ZEHN, false));
        testList3.add(new Card (Color.PIK, Type.ASS, false));
        testList3.add(new Card (Color.PIK, Type.NEUN, false));
        testList4.add(new Card (Color.KREUZ, Type.KOENIG, false));
        testList4.add(new Card (Color.HERZ, Type.NEUN, false));
        testList4.add(new Card (Color.PIK, Type.NEUN, false));
        testList4.add(new Card (Color.KREUZ, Type.ZEHN, false));
        testList5.add(new Card (Color.KREUZ, Type.BUBE, false));
        testList5.add(new Card (Color.KREUZ, Type.BUBE, false));
        testList5.add(new Card (Color.KREUZ, Type.BUBE, false));
        testList5.add(new Card (Color.KREUZ, Type.BUBE, false));
        testList5.add(new Card (Color.KREUZ, Type.DAME, false));

        SoftAssertions.assertSoftly(
                softly -> {
                    softly.assertThat(game.findHighestCard(testList1)).isEqualTo(1);
                    softly.assertThat(game.findHighestCard(testList2)).isEqualTo(0);
                    softly.assertThat(game.findHighestCard(testList3)).isEqualTo(2);
                    softly.assertThat(game.findHighestCard(testList4)).isEqualTo(3);
                    softly.assertThat(game.findHighestCard(testList5)).isEqualTo(4);
                    softly.assertThat(game.player2.getPoints()).isEqualTo(24);
                    softly.assertThat(game.player1.getPoints()).isEqualTo(10);
                    softly.assertThat(game.player3.getPoints()).isEqualTo(31);
                    softly.assertThat(game.player4.getPoints()).isEqualTo(14);
                });
    }
}
