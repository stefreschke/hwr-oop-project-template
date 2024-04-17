package hwr.oop;

import hwr.oop.exceptions.MovePieceException;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World! You started this script with the follwoing arguments: ");
    for (String arg : args) {
      System.out.println(arg);
    }
    /* ChessBoard board = new ChessBoard();
      ChessBoard.printChessBoard(board.getBoard());
      System.out.println("___________________________________");

      try {
        board.movePiece(new Position(6,0), new Position(4,0));
      } catch (MovePieceException e) {
        throw new RuntimeException(e);
      }
      ChessBoard.printChessBoard(board.getBoard());

      System.out.println("___________________________________");
      try {
        board.movePiece(new Position(1,4), new Position(3,4));
      } catch (MovePieceException e) {
        throw new RuntimeException(e);
      }
      ChessBoard.printChessBoard(board.getBoard());

      System.out.println("___________________________________");
      try {
        board.movePiece(new Position(7,0), new Position(5,0));
      } catch (MovePieceException e) {
        throw new RuntimeException(e);
      }    ChessBoard.printChessBoard(board.getBoard());
    }*/
  }
}
