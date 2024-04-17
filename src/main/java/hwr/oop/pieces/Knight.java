package hwr.oop.pieces;


import hwr.oop.Color;
import hwr.oop.Position;

public class Knight extends Piece {
  public Knight(Color color, Position position) {
    super(color, position, (color == Color.WHITE) ? 'N' : 'n');
  }
}

