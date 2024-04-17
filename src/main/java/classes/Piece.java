package classes;

import java.util.Arrays;
import java.util.List;

public class Piece {
  public enum Color {
    BLACK,
    WHITE
  }

  public enum PieceType {
    BAUER('b', Arrays.asList(Arrays.asList(0, 1)), false),
    TURM('t', Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0)), true),
    SPRINGER(
        's',
        Arrays.asList(
            Arrays.asList(2, 1),
            Arrays.asList(2, -1),
            Arrays.asList(1, 2),
            Arrays.asList(-1, 2),
            Arrays.asList(-2, 1),
            Arrays.asList(-2, -1),
            Arrays.asList(1, -2),
            Arrays.asList(-1, -2)),
        false),
    LAEUFER('l', Arrays.asList(Arrays.asList(1, 1), Arrays.asList(-1, 1)), true),
    KOENIG(
        'k',
        Arrays.asList(
            Arrays.asList(0, 1), Arrays.asList(1, 0), Arrays.asList(1, 1), Arrays.asList(-1, 1)),
        false),
    DAME(
        'd',
        Arrays.asList(
            Arrays.asList(0, 1), Arrays.asList(1, 0), Arrays.asList(1, 1), Arrays.asList(-1, 1)),
        true);

    private final char abbr;
    private final List<List<Integer>> moves;
    private final boolean moveRepeatable;

    PieceType(char abbr, List<List<Integer>> moves, boolean moveRepeatable) {
      this.abbr = abbr;
      this.moves = moves;
      this.moveRepeatable = moveRepeatable;
    }

    public char getAbbr() {
      return abbr;
    }

    public List<List<Integer>> getMoves() {
      return moves;
    }

    public boolean isMoveRepeatable() {
      return moveRepeatable;
    }
  }

  private List<Integer> actPosition;
  private List<List<Integer>> posMoves;
  private boolean moveRepeatable;
  private Color color;
  private char abbr;

  public Piece(PieceType pieceType, List<Integer> pos, Color color) {
    this.posMoves = pieceType.getMoves();
    this.moveRepeatable = pieceType.isMoveRepeatable();
    this.abbr = pieceType.getAbbr();
    this.actPosition = pos;
    this.color = color;
  }

  public void setActPosition(List<Integer> actPosition) {
    this.actPosition = actPosition;
  }

  public Color getColor() {
    return this.color;
  }

  public char getAbbr() {
    return this.abbr;
  }

  public List<Integer> getActPosition() {
    return this.actPosition;
  }

  public List<List<Integer>> getPosMoves() {
    return this.posMoves;
  }

  public boolean isMoveRepeatable() {
    return moveRepeatable;
  }
}
