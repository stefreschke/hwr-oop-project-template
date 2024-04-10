package classes;

public class Piece {
  public enum Color {
    BLACK,
    WHITE
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
