package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class MainTest : AnnotationSpec() {
  @Test
  fun `main prints hello world to stdout`() {
    val outputStream = ByteArrayOutputStream()
    val printStream = PrintStream(outputStream)
    val originalOut = System.out

    try {
      System.setOut(printStream)
      main()
      System.out.flush()
      val output = outputStream.toString().trim()
      assertThat(output).isEqualTo("Hello World!")
    } finally {
      System.setOut(originalOut)
    }
  }
}