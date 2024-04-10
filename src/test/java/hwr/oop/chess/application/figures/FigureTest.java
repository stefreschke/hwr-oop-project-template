package hwr.oop.chess.application.figures;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FigureTest {
    @Test
    void createPawn() {
        PawnFigure pawn = new PawnFigure(FigureColor.BLACK);

        Assertions.assertThat(pawn.getColor()).isEqualTo(FigureColor.BLACK);
        Assertions.assertThat(pawn.getType()).isEqualTo(FigureType.PAWN);
    }
}
