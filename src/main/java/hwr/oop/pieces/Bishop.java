package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;

public class Bishop extends Piece {
  public Bishop(Color color, Position position) {
    super(color, position, (color == Color.WHITE) ? 'B' : 'b');
  }
}
