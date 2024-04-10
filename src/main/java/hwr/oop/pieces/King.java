package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;

public class King extends Piece {
  public King(Color color, Position position) {
    super(color, position, (color == Color.WHITE) ? 'K' : 'k');
  }
}
