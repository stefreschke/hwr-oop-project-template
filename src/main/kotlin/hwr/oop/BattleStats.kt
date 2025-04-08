package hwr.oop

class BattleStats(
    private val hp : Int,
    private val speed: Int,
    private val attack: Int,
    private val defense: Int,
    private val specialAttack: Int,
    private val specialDefense: Int
) {
    fun getHp(): Int {
        return this.hp
    }

    fun getSpeed(): Int {
        return this.speed
    }

    fun getAttack(): Int {
        return this.attack
    }

    fun getDefense(): Int {
        return this.defense
    }

    fun getSpecialAttack(): Int {
        return this.specialAttack
    }

    fun getSpecialDefense(): Int {
        return this.specialDefense
    }
}