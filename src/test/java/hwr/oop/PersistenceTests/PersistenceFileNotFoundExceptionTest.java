package hwr.oop.PersistenceTests;

import hwr.oop.persistence.PersistenceFileNotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersistenceFileNotFoundExceptionTest {
    @Test
    void persistenceFileNotFoundExceptionTest() {
        PersistenceFileNotFoundException pfnfe = new PersistenceFileNotFoundException("Could not find file");
        assertThat(pfnfe.getMessage()).isEqualTo("Could not find file");
        assertThat(pfnfe).isInstanceOf(Exception.class).isInstanceOf(PersistenceFileNotFoundException.class);
    }
}
