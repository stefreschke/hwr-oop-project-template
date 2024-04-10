package hwr.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CardsTest {
  @Test
  void testAllCardsExist() {
      DoppelkopfGame game = new DoppelkopfGame();

      for (Color type : Color.values()) {
          for (Type value : Type.values()) {
              assertTrue(game.hasCard(type, value), "Karte " + type + " " + value + " fehlt im Spiel.");
          }
      }
  }
}
