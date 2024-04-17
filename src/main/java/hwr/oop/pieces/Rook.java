package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;

public class Rook extends Piece {
  public Rook(Color color, Position position) {
    super(color, position, (color == Color.WHITE) ? 'R' : 'r');
  }

}
