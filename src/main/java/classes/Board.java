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
            Piece.PieceType.TURM.getAbbr(),
            Piece.PieceType.TURM.isMoveRepeatable());
    this.board[1][0] =
        new Piece(
            new int[] {1, 0},
            Piece.PieceType.SPRINGER.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.SPRINGER.getAbbr(),
            Piece.PieceType.SPRINGER.isMoveRepeatable());
    this.board[2][0] =
        new Piece(
            new int[] {2, 0},
            Piece.PieceType.LAEUFER.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.LAEUFER.getAbbr(),
            Piece.PieceType.LAEUFER.isMoveRepeatable());
    this.board[3][0] =
        new Piece(
            new int[] {3, 0},
            Piece.PieceType.DAME.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.DAME.getAbbr(),
            Piece.PieceType.DAME.isMoveRepeatable());
    this.board[4][0] =
        new Piece(
            new int[] {4, 0},
            Piece.PieceType.KOENIG.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.KOENIG.getAbbr(),
            Piece.PieceType.KOENIG.isMoveRepeatable());
    this.board[5][0] =
        new Piece(
            new int[] {5, 0},
            Piece.PieceType.LAEUFER.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.LAEUFER.getAbbr(),
            Piece.PieceType.LAEUFER.isMoveRepeatable());
    this.board[6][0] =
        new Piece(
            new int[] {6, 0},
            Piece.PieceType.SPRINGER.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.SPRINGER.getAbbr(),
            Piece.PieceType.SPRINGER.isMoveRepeatable());
    this.board[7][0] =
        new Piece(
            new int[] {7, 0},
            Piece.PieceType.TURM.getMoves(),
            Piece.Color.WHITE,
            Piece.PieceType.TURM.getAbbr(),
            Piece.PieceType.TURM.isMoveRepeatable());
    for (int i = 0; i < 8; i++) {
      this.board[i][1] =
          new Piece(
              new int[] {i, 1},
              Piece.PieceType.BAUER.getMoves(),
              Piece.Color.WHITE,
              Piece.PieceType.BAUER.getAbbr(),
              Piece.PieceType.BAUER.isMoveRepeatable());
    }
    this.board[0][7] =
        new Piece(
            new int[] {0, 7},
            Piece.PieceType.TURM.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.TURM.getAbbr(),
            Piece.PieceType.TURM.isMoveRepeatable());
    this.board[1][7] =
        new Piece(
            new int[] {1, 7},
            Piece.PieceType.SPRINGER.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.SPRINGER.getAbbr(),
            Piece.PieceType.SPRINGER.isMoveRepeatable());
    this.board[2][7] =
        new Piece(
            new int[] {2, 7},
            Piece.PieceType.LAEUFER.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.LAEUFER.getAbbr(),
            Piece.PieceType.LAEUFER.isMoveRepeatable());
    this.board[3][7] =
        new Piece(
            new int[] {3, 7},
            Piece.PieceType.DAME.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.DAME.getAbbr(),
            Piece.PieceType.DAME.isMoveRepeatable());
    this.board[4][7] =
        new Piece(
            new int[] {4, 7},
            Piece.PieceType.KOENIG.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.KOENIG.getAbbr(),
            Piece.PieceType.KOENIG.isMoveRepeatable());
    this.board[5][7] =
        new Piece(
            new int[] {5, 7},
            Piece.PieceType.LAEUFER.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.LAEUFER.getAbbr(),
            Piece.PieceType.LAEUFER.isMoveRepeatable());
    this.board[6][7] =
        new Piece(
            new int[] {6, 7},
            Piece.PieceType.SPRINGER.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.SPRINGER.getAbbr(),
            Piece.PieceType.SPRINGER.isMoveRepeatable());
    this.board[7][7] =
        new Piece(
            new int[] {7, 7},
            Piece.PieceType.TURM.getMoves(),
            Piece.Color.BLACK,
            Piece.PieceType.TURM.getAbbr(),
            Piece.PieceType.TURM.isMoveRepeatable());
    for (int i = 0; i < 8; i++) {
      this.board[i][1] =
          new Piece(
              new int[] {i, 1},
              Piece.PieceType.BAUER.getMoves(),
              Piece.Color.WHITE,
              Piece.PieceType.BAUER.getAbbr(),
              Piece.PieceType.BAUER.isMoveRepeatable());
    }
  }

  /*public void changePos(int oldX, int oldY, int newX, int newY) {
    if (this.board[oldX][oldY] == null) {
      // TODO: log an error
      return;
    }
    if (!isValidMove(this.board[oldX][oldY], newX, newY)) {
      // TODO: log an error
      return;
    }
    if (this.board[newX][newY] != null) {
      // TODO: log piece captured
    }
    this.board[newX][newY] = this.board[oldX][oldY];
    this.board[oldX][oldY] = null;
  }*/

  public boolean isValidMove(Piece piece, int x, int y) {
    int[] vec = new int[] {x - piece.getActPosition()[0], y - piece.getActPosition()[1]};
    for (int[] i : piece.getPosMoves()) {
      if (piece.isMoveRepeatable()) {
        for (int j = -7; j < 8; j++) {
          if (j == 0) {
            continue;
          }
          if (i[0] * j == vec[0] && i[1] * j == vec[1]) {
            return true;
          }
        }
      } else {
        if (i[0] == vec[0] && i[1] == vec[1]) {
          return true;
        }
      }
    }
    return false;
  }

  public Piece getPieceAt(int x, int y) {
    return this.board[x][y];
  }

  public Piece[][] getBoard() {
    return board;
  }

  public void printBoard() {
    System.out.println("Printing Board here");
  }
}
