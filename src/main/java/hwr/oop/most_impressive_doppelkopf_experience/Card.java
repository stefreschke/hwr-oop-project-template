package hwr.oop.most_impressive_doppelkopf_experience;

import java.io.Serializable;

public class Card implements Serializable {
  private static final long serialVersionUID = 1L;
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

  public int getWorth() {
    return worth;
  }

  final int worth; // Worth of the Card
  final String name;

  public Card(CardSymbols symbol, CardColours colour, int value, String name, int worth) {
    this.symbol = symbol;
    this.colour = colour;
    this.value = value;
    this.name = name;
    this.worth = worth;
  }

  @Override // aus einem Objekten ein String
  public String toString() {
    return "Card{" +
            "symbol=" + symbol +
            ", colour=" + colour +
            '}';
  }
}
