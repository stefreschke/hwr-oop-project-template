package hwr.oop;

import hwr.oop.pieces.Piece;

public class Main {
  public static void main(String[] args) {
    ChessBoard chessBoard = new ChessBoard();
    printChessBoard(chessBoard.getBoard());

    // Beispiel-Zug: Bewegung der Figur 'Q' von (7, 3) nach (5, 3)
    chessBoard.movePiece(new Position(7, 3), new Position(5, 3));

    // Bewege den linken weißen Turm um 4 Felder nach vorne
    chessBoard.movePiece(new Position(7, 0), new Position(3, 0));



    System.out.println(); // Leerzeile für bessere Lesbarkeit
    printChessBoard(chessBoard.getBoard());
  }

  public static void printChessBoard(Piece[][] board) {
    System.out.println("  a b c d e f g h");
    System.out.println(" +-----------------+");
    for (int i = 0; i < 8; i++) {
      System.out.print(8 - i + "| ");
      for (int j = 0; j < 8; j++) {
        if (board[i][j] != null) {
          System.out.print(board[i][j].getSymbol() + " ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println("|");
    }
    System.out.println(" +-----------------+");
  }
}
