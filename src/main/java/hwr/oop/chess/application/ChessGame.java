package hwr.oop.chess.application;

import hwr.oop.chess.application.figures.Figure;
import hwr.oop.chess.application.figures.FigureColor;
import hwr.oop.chess.application.figures.PawnFigure;
import hwr.oop.chess.application.figures.RookFigure;
import hwr.oop.chess.application.figures.QueenFigure;
import hwr.oop.chess.application.figures.KingFigure;
import hwr.oop.chess.application.figures.KnightFigure;
import hwr.oop.chess.application.figures.BishopFigure;

import java.util.ArrayList;

public class ChessGame {
    void startNewGame() {
        ArrayList<Figure> list = new ArrayList<>();


        // White Player
        list.add(new RookFigure  (FigureColor.WHITE, new Position(1, 1)));
        list.add(new KnightFigure(FigureColor.WHITE, new Position(2, 1)));
        list.add(new BishopFigure(FigureColor.WHITE, new Position(3, 1)));
        list.add(new QueenFigure (FigureColor.WHITE, new Position(4, 1)));
        list.add(new KingFigure  (FigureColor.WHITE, new Position(5, 1)));
        list.add(new BishopFigure(FigureColor.WHITE, new Position(6, 1)));
        list.add(new KnightFigure(FigureColor.WHITE, new Position(7, 1)));
        list.add(new RookFigure  (FigureColor.WHITE, new Position(8, 1)));

        for(int x = 1; x <= 8; x++) {
            list.add(new PawnFigure(FigureColor.WHITE, new Position(x, 2)));
        }

        // Black Player
        list.add(new RookFigure  (FigureColor.BLACK, new Position(1, 8)));
        list.add(new KnightFigure(FigureColor.BLACK, new Position(2, 8)));
        list.add(new BishopFigure(FigureColor.BLACK, new Position(3, 8)));
        list.add(new QueenFigure (FigureColor.BLACK, new Position(4, 8)));
        list.add(new KingFigure  (FigureColor.BLACK, new Position(5, 8)));
        list.add(new BishopFigure(FigureColor.BLACK, new Position(6, 8)));
        list.add(new KnightFigure(FigureColor.BLACK, new Position(7, 8)));
        list.add(new RookFigure  (FigureColor.BLACK, new Position(8, 8)));

        for(int x = 1; x <= 8; x++) {
            list.add(new PawnFigure(FigureColor.BLACK, new Position(x, 7)));
        }

        Board board = new Board(list);

    }
}
