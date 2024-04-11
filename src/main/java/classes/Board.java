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
        new Piece(
            new int[] {0, 0},
            Piece.PieceType.TURM.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.TURM.getAbbr());
    this.board[1][0] =
        new Piece(
            new int[] {1, 0},
            Piece.PieceType.SPRINGER.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.SPRINGER.getAbbr());
    this.board[2][0] =
        new Piece(
            new int[] {2, 0},
            Piece.PieceType.LAEUFER.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.LAEUFER.getAbbr());
    this.board[3][0] =
        new Piece(
            new int[] {3, 0},
            Piece.PieceType.DAME.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.DAME.getAbbr());
    this.board[4][0] =
        new Piece(
            new int[] {4, 0},
            Piece.PieceType.KOENIG.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.KOENIG.getAbbr());
    this.board[5][0] =
        new Piece(
            new int[] {5, 0},
            Piece.PieceType.LAEUFER.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.LAEUFER.getAbbr());
    this.board[6][0] =
        new Piece(
            new int[] {6, 0},
            Piece.PieceType.SPRINGER.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.SPRINGER.getAbbr());
    this.board[7][0] =
        new Piece(
            new int[] {7, 0},
            Piece.PieceType.TURM.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.TURM.getAbbr());

    for (int i = 0; i < 8; i++) {
      this.board[i][1] =
          new Piece(
              new int[] {i, 1},
              Piece.PieceType.BAUER.getMoves(),
              Piece.Color.WHITE,
              Piece.PieceType.BAUER.getAbbr());
    }

    this.board[0][7] =
        new Piece(
            new int[] {0, 7},
            Piece.PieceType.TURM.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.TURM.getAbbr());
    this.board[1][7] =
        new Piece(
            new int[] {1, 7},
            Piece.PieceType.SPRINGER.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.SPRINGER.getAbbr());
    this.board[2][7] =
        new Piece(
            new int[] {2, 7},
            Piece.PieceType.LAEUFER.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.LAEUFER.getAbbr());
    this.board[3][7] =
        new Piece(
            new int[] {3, 7},
            Piece.PieceType.DAME.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.DAME.getAbbr());
    this.board[4][7] =
        new Piece(
            new int[] {4, 7},
            Piece.PieceType.KOENIG.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.KOENIG.getAbbr());
    this.board[5][7] =
        new Piece(
            new int[] {5, 7},
            Piece.PieceType.LAEUFER.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.LAEUFER.getAbbr());
    this.board[6][7] =
        new Piece(
            new int[] {6, 7},
            Piece.PieceType.SPRINGER.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.SPRINGER.getAbbr());
    this.board[7][7] =
        new Piece(
            new int[] {7, 7},
            Piece.PieceType.TURM.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.TURM.getAbbr());

    for (int i = 0; i < 8; i++) {
      this.board[i][6] = new Piece(new int[] {i, 6}, new int[][] {{0, 1}}, Piece.Color.BLACK, 'b');
    }
  }

  public void changePos(int oldX, int oldY, int newX, int newY) {
    if (this.board[oldX][oldY] == null) {
      // TODO: log an error
      return;
    }
    if (!this.board[oldX][oldY].isValidMove(newX, newY)) {
      // TODO: log an error
      return;
    }
    if (this.board[newX][newY] != null) {
      // TODO: log piece captured
    }
    this.board[newX][newY] = this.board[oldX][oldY];
    this.board[oldX][oldY] = null;
  }

  public Piece[][] getBoard() {
    return board;
  }

  public void printBoard() {
    System.out.println("Printing Board here");
  }
}
