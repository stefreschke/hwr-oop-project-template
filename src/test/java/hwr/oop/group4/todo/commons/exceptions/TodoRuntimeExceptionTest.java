package hwr.oop.group4.todo.commons.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoRuntimeExceptionTest {

    @Test
    void runTimeExceptionWithNull(){
        assertThat(new TodoRuntimeException()).hasMessage(null).hasCause(null);
    }

    @Test
    void runTimeExceptionWithMessage(){
        assertThat(new TodoRuntimeException("sjs")).hasMessage("sjs").hasCause(null);
    }

    @Test
    void runTimeExceptionWithCauseAndMessage(){
        assertThat(new TodoRuntimeException("test", new RuntimeException())).hasMessage("test").hasCause(new RuntimeException());
    }

    @Test
    void runTimeExceptionJustCause(){
        assertThat(new TodoRuntimeException(new RuntimeException())).hasCause(new RuntimeException());
    }

    @Test
    void runTimeExceptionAll() {
        assertThat(new TodoRuntimeException("test", null, true, true)).hasMessage("test").hasCause(null);
    }

}