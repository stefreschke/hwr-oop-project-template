package hwr.oop.chess.application;

import hwr.oop.chess.application.figures.Figure;
import hwr.oop.chess.application.figures.FigureColor;

import java.util.ArrayList;

public class Board {
    private static ArrayList<Figure> figures;
    public Board(ArrayList<Figure> figures){
        Board.figures = figures;
    }
    public static void setFigures(ArrayList<Figure> figures) {
        Board.figures = figures;
    }

    public static Figure getFigureOnField(Position position) {
        for (Figure figure : figures) {
          if (figure.isOnField(position)) {
            return figure;
          }
        }
        return null;
    }

    public static boolean isFigureOnField(Position position) {
        return Board.getFigureOnField(position) != null;
    }
}
