package classes;

public class Piece {
  public enum Color {
    BLACK,
    WHITE
  }

  public enum PieceType {
    BAUER('b', new int[][] {{0, 1}}, false),
    TURM('t', new int[][] {{0, 1}, {1, 0}}, true),
    SPRINGER('s', new int[][] {{2, 1}, {2, -1}, {1, 2}, {-1, 2}}, false),
    LAEUFER('l', new int[][] {{1, 1}, {-1, 1}}, true),
    KOENIG('k', new int[][] {{0, 1}, {1, 0}, {1, 1}, {-1, 1}}, false),
    DAME('d', new int[][] {{0, 1}, {1, 0}, {1, 1}, {-1, 1}}, true);

    private final char abbr;
    private final int[][] moves;
    private final boolean moveRepeatable;

    PieceType(char abbr, int[][] moves, boolean moveRepeatable) {
      this.abbr = abbr;
      this.moves = moves;
      this.moveRepeatable = moveRepeatable;
    }

    public char getAbbr() {
      return abbr;
    }

    public int[][] getMoves() {
      return moves;
    }

    public boolean isMoveRepeatable() {
      return moveRepeatable;
    }
  }

  private int[] actPosition;
  private int[][] posMoves;
  private boolean moveRepeatable;
  private Color color;
  private char abbr;

  public Piece(int[] pos, int[][] moves, Color color, char abbr, boolean moveRepeatable) {
    this.actPosition = pos;
    this.posMoves = moves;
    this.moveRepeatable = moveRepeatable;
    this.color = color;
    this.abbr = abbr;
  }

  public boolean isBlocked() {
    return false;
  }

  public void setActPosition(int[] actPosition) {
    this.actPosition = actPosition;
  }

  public Color getColor() {
    return this.color;
  }

  public char getAbbr() {
    return this.abbr;
  }

  public int[] getActPosition() {
    return this.actPosition;
  }

  public int[][] getPosMoves() {
    return this.posMoves;
  }

  public boolean isMoveRepeatable() {
    return moveRepeatable;
  }
}
