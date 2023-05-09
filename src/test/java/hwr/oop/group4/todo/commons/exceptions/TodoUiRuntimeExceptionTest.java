package hwr.oop.group4.todo.commons.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoUiRuntimeExceptionTest {
    @Test
    void runTimeExceptionNothing() {
        assertThat(new TodoUiRuntimeException()).hasMessage(null).hasCause(null);
    }

    @Test
    void runTimeException() {
        assertThat(new TodoUiRuntimeException("sjs")).hasMessage("sjs").hasCause(null);
    }

    @Test
    void runTimeExceptionWithCause() {
        assertThat(new TodoUiRuntimeException("test", null)).hasMessage("test").hasCause(null);
    }

    @Test
    void runTimeExceptionNothingCause() {

        assertThat(new TodoUiRuntimeException(new RuntimeException())).hasCause(new RuntimeException());
    }

    @Test
    void runTimeExceptionAll() {

        assertThat(new TodoUiRuntimeException("test", null, true, true)).hasMessage("test").hasCause(null);

    }
}