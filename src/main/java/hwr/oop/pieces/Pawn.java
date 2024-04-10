package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;

public class Pawn extends Piece {
  public Pawn(Color color, Position position) {
    super(color, position, (color == Color.WHITE) ? 'P' : 'p');
  }
}
