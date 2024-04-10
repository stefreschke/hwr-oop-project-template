package hwr.oop;

public class Main {
  public static void main(String[] args) {
    ChessBoard chessBoard = new ChessBoard();
    printChessBoard(chessBoard.getBoard());
    Position from = new Position(7, 3);
    Position to = new Position(5, 3);
    boolean moveSuccessful = chessBoard.movePiece(from, to);
    if (moveSuccessful) {
      System.out.println("Die Figur 'Q' wurde erfolgreich von (7, 3) nach (5, 3) bewegt.");
    } else {
      System.out.println("Die Bewegung der Figur 'Q' von (7, 3) nach (5, 3) war ungültig.");
    }
    printChessBoard(chessBoard.getBoard());
  }

  public static void printChessBoard(Piece[][] board) {
    System.out.println("  a b c d e f g h");
    System.out.println(" +-----------------+");
    for (int i = 0; i < 8; i++) {
      System.out.print(8 - i + "| ");
      for (int j = 0; j < 8; j++) {
        if (board[i][j] != null) {
          System.out.print(board[i][j].getName() + " ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println("|");
    }
    System.out.println(" +-----------------+");
  }

}
