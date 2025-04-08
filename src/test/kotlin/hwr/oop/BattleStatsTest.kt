package hwr.oop

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BattleStatsTest : AnnotationSpec() {
    private val battleStats = BattleStats(200, 100, 30, 30, 40, 40)

    @Test
    fun `test battleStats has HP`() {
        assertThat(battleStats.getHp()).isEqualTo(200)
    }

    @Test
    fun `test battleStats has speed`() {
        assertThat(battleStats.getSpeed()).isEqualTo(100)
    }

    @Test
    fun `test battleStats has attack`() {
        assertThat(battleStats.getAttack()).isEqualTo(30)
    }

    @Test
    fun `test battleStats has defense`() {
        assertThat(battleStats.getDefense()).isEqualTo(30)
    }

    @Test
    fun `test battleStats has special attack`() {
        assertThat(battleStats.getSpecialAttack()).isEqualTo(40)
    }

    @Test
    fun `test battleStats has special defense`() {
        assertThat(battleStats.getSpecialDefense()).isEqualTo(40)
    }
}