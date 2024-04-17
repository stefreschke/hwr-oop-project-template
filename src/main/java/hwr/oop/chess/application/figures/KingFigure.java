package hwr.oop.chess.application.figures;

import hwr.oop.chess.application.Position;

public class KingFigure implements Figure {
    Position startPosition = null;
    Position currentPosition = null;
    FigureType type = null;
    FigureColor color = null;

    public KingFigure(FigureColor color, Position position) {
        this.type = FigureType.KING;
        this.color = color;
        this.startPosition = position;
        this.currentPosition = position;
    }


    public boolean canMoveTo(Position newPosition) {

        return true;
    }
}
