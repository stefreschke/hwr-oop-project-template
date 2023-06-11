package hwr.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StateTest {
    @Test
    void nextStateTest() {
        assertThat(State.TODO.nextState()).isEqualTo(State.IN_PROGRESS);
        assertThat(State.IN_PROGRESS.nextState()).isEqualTo(State.DONE);
        assertThat(State.ON_HOLD.nextState()).isEqualTo(State.IN_PROGRESS);
        assertThat(State.DONE.nextState()).isEqualTo(State.DONE);
    }
    @Test
    void previousStateTest() {
        assertThat(State.TODO.previousState()).isEqualTo(State.TODO);
        assertThat(State.IN_PROGRESS.previousState()).isEqualTo(State.TODO);
        assertThat(State.ON_HOLD.previousState()).isEqualTo(State.TODO);
        assertThat(State.DONE.previousState()).isEqualTo(State.IN_PROGRESS);
    }
    @Test
    void holdTest() {
        assertThat(State.TODO.hold()).isEqualTo(State.TODO);
        assertThat(State.IN_PROGRESS.hold()).isEqualTo(State.ON_HOLD);
        assertThat(State.ON_HOLD.hold()).isEqualTo(State.ON_HOLD);
        assertThat(State.DONE.hold()).isEqualTo(State.DONE);
    }
}
