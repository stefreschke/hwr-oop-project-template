package classes;

public class Board {
  private Piece[][] board;

  public Board() {
    board = new Piece[8][8];
  }

  public void initBoard() {
    for (Piece[] p : board) {
      for (Piece i : p) {
        i = null;
      }
    }

    this.board[0][0] =
        new Piece(new int[] {0, 0}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.WHITE, 't');
    this.board[1][0] =
        new Piece(new int[] {1, 0}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.WHITE, 's');
    this.board[2][0] =
        new Piece(new int[] {2, 0}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.WHITE, 'l');
    this.board[3][0] =
        new Piece(new int[] {3, 0}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.WHITE, 'd');
    this.board[4][0] =
        new Piece(new int[] {4, 0}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.WHITE, 'k');
    this.board[5][0] =
        new Piece(new int[] {5, 0}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.WHITE, 'l');
    this.board[6][0] =
        new Piece(new int[] {6, 0}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.WHITE, 's');
    this.board[7][0] =
        new Piece(new int[] {7, 0}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.WHITE, 't');

    for (int i = 0; i < 8; i++) {
      this.board[i][1] = new Piece(new int[] {i, 1}, new int[][] {{0, 1}}, Piece.Color.WHITE, 'b');
    }

    this.board[0][8] =
        new Piece(new int[] {0, 8}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.BLACK, 't');
    this.board[1][8] =
        new Piece(new int[] {1, 8}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.BLACK, 's');
    this.board[2][8] =
        new Piece(new int[] {2, 8}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.BLACK, 'l');
    this.board[3][8] =
        new Piece(new int[] {3, 8}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.BLACK, 'd');
    this.board[4][8] =
        new Piece(new int[] {4, 8}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.BLACK, 'k');
    this.board[5][8] =
        new Piece(new int[] {5, 8}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.BLACK, 'l');
    this.board[6][8] =
        new Piece(new int[] {6, 8}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.BLACK, 's');
    this.board[7][8] =
        new Piece(new int[] {7, 8}, new int[][] {{0, 1}, {1, 0}}, Piece.Color.BLACK, 't');

    for (int i = 0; i < 8; i++) {
      this.board[i][7] = new Piece(new int[] {i, 7}, new int[][] {{0, 1}}, Piece.Color.BLACK, 'b');
    }
  }

  public void changePos() {}
}
