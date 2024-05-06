package hwr.oop.doppelkopf.group6;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

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
            testList1.add(new Card (Color.HERZ, Type.ASS, false, "HA"));
            testList1.add(new Card (Color.HERZ, Type.BUBE, true, "HB"));
            testList1.add(new Card (Color.PIK, Type.ASS, false, "PA"));
            testList2.add(new Card (Color.KREUZ, Type.ZEHN, false, "Kr10"));
            testList3.add(new Card (Color.PIK, Type.ZEHN, false, "P10"));
            testList3.add(new Card (Color.PIK, Type.ZEHN, false, "P10"));
            testList3.add(new Card (Color.PIK, Type.ASS, false, "PA"));
            testList3.add(new Card (Color.PIK, Type.NEUN, false, "P9"));
            testList4.add(new Card (Color.KREUZ, Type.KOENIG, false, "KrK"));
            testList4.add(new Card (Color.HERZ, Type.NEUN, false, "H9"));
            testList4.add(new Card (Color.PIK, Type.NEUN, false, "P9"));
            testList4.add(new Card (Color.KREUZ, Type.ZEHN, false, "Kr10"));
            testList5.add(new Card (Color.KREUZ, Type.BUBE, false, "KrB"));
            testList5.add(new Card (Color.KREUZ, Type.BUBE, false, "KrB"));
            testList5.add(new Card (Color.KREUZ, Type.BUBE, false, "KrB"));
            testList5.add(new Card (Color.KREUZ, Type.BUBE, false, "KrB"));
            testList5.add(new Card (Color.KREUZ, Type.DAME, false, "KrD"));

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
