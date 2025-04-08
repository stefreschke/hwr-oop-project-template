package hwr.oop

import hwr.oop.Card

class Deck {

    private val suits = listOf("Hearts", "Diamonds", "Clubs", "Spades")
    private val ranks = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")

    private val originalOrder: List<Card>
    private val cards: MutableList<Card>

    init {
        val tempList = mutableListOf<Card>()

        for (suit in suits) {
            for (rank in ranks) {
                val card = Card(suit, rank)
                tempList.add(card)
            }
        }

        originalOrder = tempList.toList()
        cards = tempList.toMutableList()
    }

    /*fun peak (): String {
        for (card in cards) {
            val cardTitle = (card.rank + " " + card.suit + "\n")

        }
        return cardTitle
    }*/


    fun peak2 (): List<Card> {
        return cards
    }



    fun deckShuffle(): Boolean {
        val beforeShuffle = cards.toList()
        cards.shuffle()
        return beforeShuffle != cards
    }
}
