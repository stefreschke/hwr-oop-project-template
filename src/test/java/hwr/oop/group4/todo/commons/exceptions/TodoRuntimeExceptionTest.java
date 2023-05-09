package hwr.oop.group4.todo.commons.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoRuntimeExceptionTest {

    @Test
    void runTimeExceptionNothing(){

        assertThat(new TodoRuntimeException()).hasMessage(null).hasCause(null);
    }

    @Test
    void runTimeException(){

        assertThat(new TodoRuntimeException("sjs")).hasMessage("sjs").hasCause(null);
    }

    @Test
    void runTimeExceptionWithCause(){
        assertThat(new TodoRuntimeException("test", null)).hasMessage("test").hasCause(null);
    }

    @Test
    void runTimeExceptionWithOnlyCause(){
        assertThat(new TodoRuntimeException(new RuntimeException())).hasCause(new RuntimeException());
    }

    @Test
    void runTimeExceptionAll() {
        assertThat(new TodoRuntimeException("test", null, true, true)).hasMessage("test").hasCause(null);

    }

}