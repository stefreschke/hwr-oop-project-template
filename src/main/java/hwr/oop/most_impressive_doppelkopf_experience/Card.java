package hwr.oop.most_impressive_doppelkopf_experience;

import hwr.oop.most_impressive_doppelkopf_experience.enums.CardColours;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;

public class Card {
  public CardSymbols getSymbol() {
    return symbol;
  }

  public CardColours getColour() {
    return colour;
  }

  public int getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  final CardSymbols symbol;
  final CardColours colour;
  final int value; // Strength of the card
  final int worth; // Worth of the Card
  final String name;

  public Card(CardSymbols symbol, CardColours colour, int value, String name, int worth) {
    this.symbol = symbol;
    this.colour = colour;
    this.value = value;
    this.name = name;
    this.worth = worth;
  }
}
