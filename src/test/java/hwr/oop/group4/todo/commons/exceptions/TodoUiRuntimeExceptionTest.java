package hwr.oop.group4.todo.commons.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoUiRuntimeExceptionTest {
    @Test
    void runTimeExceptionWithNull() {
        assertThat(new TodoUiRuntimeException()).hasMessage(null).hasCause(null);
    }

    @Test
    void runTimeExceptionWithMessage() {
        assertThat(new TodoUiRuntimeException("sjs")).hasMessage("sjs").hasCause(null);
    }

    @Test
    void runTimeExceptionWithMessageAndCause() {
        assertThat(new TodoUiRuntimeException("test", new RuntimeException())).hasMessage("test").hasCause(new RuntimeException());
    }

    @Test
    void runTimeExceptionJustCause() {
        assertThat(new TodoUiRuntimeException(new RuntimeException())).hasCause(new RuntimeException());
    }

    @Test
    void runTimeExceptionAll() {
        assertThat(new TodoUiRuntimeException("test", null, true, true)).hasMessage("test").hasCause(null);
    }
}