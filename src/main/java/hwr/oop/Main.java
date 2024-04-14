package hwr.oop;

import hwr.oop.exceptions.ChessBoardException;
import hwr.oop.pieces.Piece;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws ChessBoardException {
//    ChessBoard chessBoard = new ChessBoard();
//    printChessBoard(chessBoard.getBoard());
//
//    Scanner scanner = new Scanner(System.in);
//
//    // Loop for multiple moves
//    boolean continueGame = true;
//    while (continueGame) {
//      System.out.println("Enter the current position (e.g., 'a1'):");
//      String fromInput = scanner.nextLine();
//      System.out.println("Enter the target position (e.g., 'a3'):");
//      String toInput = scanner.nextLine();
//
//      Position from = convertInputToPosition(fromInput);
//      Position to = convertInputToPosition(toInput);
//      System.out.println("left white Rook: "+ chessBoard.getBoard()[7][0].toString());
//      chessBoard.movePiece(from, to);
//      System.out.println("left white Rook: "+ chessBoard.getBoard()[5][0].toString());
//
//      System.out.println(); // Blank line for readability
//      printChessBoard(chessBoard.getBoard());
//
//      // Check if the user wants to continue
//      System.out.println("Do you want to make another move? (yes/no)");
//      String continueInput = scanner.nextLine();
//      continueGame = continueInput.equalsIgnoreCase("yes");
//    }
//
//    // Close the scanner to release resources
//    scanner.close();
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
    System.out.println("  a b c d e f g h");
  }

  public static Position convertInputToPosition(String input) throws ChessBoardException {
    if (input.length() != 2 || !Character.isLetter(input.charAt(0)) || !Character.isDigit(input.charAt(1))) {
      throw new ChessBoardException("Invalid input format. Please provide a valid position (e.g., 'a1').");
    }
    int column = input.charAt(0) - 'a';
    int row = 8 - Character.getNumericValue(input.charAt(1));

    if (column < 0 || column >= 8 || row < 0 || row >= 8) {
      throw new ChessBoardException("Invalid position. Position must be within the chessboard.");
    }

    return new Position(row, column);
  }
}
