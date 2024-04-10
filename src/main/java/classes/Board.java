package classes;

public class Board {
  private Piece[][] board;

  public Board() {
    board = new Piece[8][8];
  }

  public void initBoard() {
    int[] pos = {0, 0};
    int[][] moves = {{0, 1}, {1, 0}};
    this.board[0][0] = new Piece(pos, moves, Piece.Color.WHITE, 't');
  }
}
