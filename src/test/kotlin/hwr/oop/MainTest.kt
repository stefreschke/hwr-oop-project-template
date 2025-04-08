package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat
import hwr.oop.*

class DeckTest : AnnotationSpec() {

   /* @Test
    fun `Deck is shuffled` () {
        val deck = Deck()
        val isShuffled = deck.deckShuffle()
        assertThat(isShuffled).isTrue()
    } */

    @Test
    fun `Deck is shuffled` () {
        val deck = Deck()
        //val before = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val before = deck.peak2()
        val afterShuffled = before.shuffled()

        assertThat(afterShuffled).doesNotContainSequence(before)
    }
}