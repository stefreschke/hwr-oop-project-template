package hwr.oop

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

// TODO Delete this placeholder test.
internal class KotlinExampleTest {
    @Test
    fun get_IsTrue() {
        val example = KotlinExample()
        val result = example.get()
        Assertions.assertThat(result).isTrue
    }
}