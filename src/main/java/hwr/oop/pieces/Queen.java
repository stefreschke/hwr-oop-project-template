package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;

public class Queen extends Piece {
  public Queen(Color color, Position position) {
    super(color, position, (color == Color.WHITE) ? 'Q' : 'q');
  }
}
