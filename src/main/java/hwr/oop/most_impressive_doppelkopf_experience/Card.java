package hwr.oop.most_impressive_doppelkopf_experience;

import java.io.Serial;
import java.io.Serializable;

public class Card implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

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
  private final CardColours colour;
  private int value; // Strength of the card

  public int getWorth() {
    return worth;
  }

  public void setValue(int value) {
    this.value = value;
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

  @Override
  public String toString() {
    return "Card{" + "symbol=" + symbol + ", colour=" + colour + '}';
  }
}
