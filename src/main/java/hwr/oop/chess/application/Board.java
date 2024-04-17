package hwr.oop.chess.application;

import hwr.oop.chess.application.figures.Figure;
import hwr.oop.chess.application.figures.FigureColor;

import java.util.ArrayList;

public class Board {
    private static ArrayList<Figure> figures;
   // private Figure[][] board = new Figure[8][8];
    public Board(ArrayList<Figure> figures){
        Board.figures = figures;
    }
    public static void setFigures(ArrayList<Figure> figures) {
        Board.figures = figures;
    }

    public static Figure getFigureOnField(Position position) {
        for (Figure figure : figures) {
          if (figure.getPosition() == position && !figure.isCaptured()) {
            return figure;
          }
        }
        return null;
    }

    public static boolean isFigureOnField(Position position) {
        return Board.getFigureOnField(position) != null;
    }


    public static void moveFigure(Position from, Position to) throws Exception {
        Figure figure = Board.getFigureOnField(from);
        if(figure == null) {
            throw new Exception("On this field there is no Figure!");
        }

        if(!figure.canMoveTo(to)) {
            throw new Exception("The Figure can't move there!");
        }

        figure.setPosition(to);
    }
}
