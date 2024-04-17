package hwr.oop.chess.application.figures;

import hwr.oop.chess.application.Position;

public class KnightFigure implements Figure {
    Position startPosition = null;
    Position currentPosition = null;
    FigureType type = null;
    FigureColor color = null;

    public KnightFigure(FigureColor color, Position position) {
        this.type = FigureType.KNIGHT;
        this.color = color;
        this.startPosition = position;
        this.currentPosition = position;
    }

    @Override
    public boolean canMoveTo(Position newPosition) {

        return true;
    }

    @Override
    public void moveTo(Position newPosition) {
        if(canMoveTo(newPosition)) {
            this.currentPosition = newPosition;
        }
    }

    @Override
    public boolean isOnField(Position field) {
        return this.currentPosition == field;
    }

    @Override
    public boolean isCaptured() {
        return this.currentPosition == null;
    }

    @Override
    public Position getPosition() {
        return this.currentPosition;
    }

    @Override
    public void setPosition(Position position) {
        this.currentPosition = position;
    }

    @Override
    public FigureColor getColor() {
        return this.color;
    }

    @Override
    public FigureType getType() {
        return this.type;
    }
}
