package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// TODO Delete this placeholder test.
class BowlingGameTest {
    @Test
    void get_IsTrue() {
        BowlingGame example = new BowlingGame();
        boolean result = example.get();
        Assertions.assertThat(result).isTrue();
    }
}