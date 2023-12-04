package day4

import readInputDay4
import kotlin.math.pow

fun main() {
    val testInput = readInputDay4("Day04_test")

    println("part 1: ${part1(testInput)}")
    println("part 2: ${part2(testInput)}")
}

fun part2(input: List<String>): Int {
    val cards = input.map { line -> Card.fromString(line) }
    val counts = IntArray(cards.size) { _ -> 1 }

    for (card in cards) {
        for (j in card.id..<card.id+card.countMatches()) {
            if (j >= counts.size) break
            counts[j] += counts[card.id - 1]
        }
    }
    return counts.sum()
}

fun part1(input: List<String>): Int {
    return input.sumOf {
        Card.fromString(it).calculatePoints()
    }
}

class Card(val id: Int, val winning: Set<Int>, val selected: Set<Int>, var count: Int = 1) {

    fun countMatches(): Int {
        return winning.intersect(selected).size
    }

    fun calculatePoints(): Int {
        val matches = countMatches()
        return if (matches == 0) 0 else 2.0.pow(matches - 1).toInt()
    }

    companion object {
        fun fromString(card: String): Card {
            val cardRegex = "^Card +(\\d+): (.*)$".toRegex()

            val (cardId, cardResults) = cardRegex.find(card)!!.destructured
            val (winningString, selectedString) = cardResults.split("|")

            val winning = winningString.split(' ').filter { it.isNotEmpty() }.map { it.toInt() }.toSet()
            val selected = selectedString.split(' ').filter { it.isNotEmpty() }.map { it.toInt() }.toSet()

            return Card(cardId.toInt(), winning = winning, selected = selected)
        }
    }
}