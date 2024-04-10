package hwr.oop.chess.application.figures;

public abstract class Figure {
    private final FigureType type;

    private final FigureColor color;

    Figure(FigureType type, FigureColor color) {
        this.type = type;
        this.color = color;
    }

    public FigureColor getColor() {
        return color;
    }

    public FigureType getType() {
        return type;
    }
}
