package classes;

public class Piece {
  public enum Color {
    BLACK,
    WHITE
  }

  public enum PieceType {
    BAUER('b', new int[][] {{0, 1}}),
    TURM('t', new int[][] {{0, 1}, {1, 0}}),
    SPRINGER('s', new int[][] {{2, 1}, {2, -1}, {1, 2}, {-1, 2}}),
    LAEUFER('l', new int[][] {{1, 1}, {-1, 1}}),
    KOENIG('k', new int[][] {{0, 1}, {1, 0}, {1, 1}, {-1, 1}}),
    DAME('d', new int[][] {{0, 1}, {1, 0}, {1, 1}, {-1, 1}});

    private final char abbr;
    private final int[][] moves;

    PieceType(char abbr, int[][] moves) {
      this.abbr = abbr;
      this.moves = moves;
    }

    public char getAbbr() {
      return abbr;
    }

    public int[][] getMoves() {
      return moves;
    }
  }

  private int[] actPosition;
  private int[][] posMoves;
  private Color color;
  private char abbr;

  public Piece(int[] pos, int[][] moves, Color color, char abbr) {
    this.actPosition = pos;
    this.posMoves = moves;
    this.color = color;
    this.abbr = abbr;
  }

  public boolean isValidMove(int x, int y) {
    return false;
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
}
