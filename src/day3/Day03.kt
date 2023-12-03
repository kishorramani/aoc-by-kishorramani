package day3

import readInputDay3

fun main() {
    val testInput = readInputDay3("Day03_test")

    println("part 1: ${part1(testInput)}")
    println("part 2: ${part2(testInput)}")
}

fun part1(input: List<String>): Int {
    val symbols = symbolValue(input)
    val numRegex = "(\\d+)".toRegex()
    var sum = 0
    for (y in input.indices) {
        numRegex.findAll(input[y]).forEach { match ->
            if (adjacentValue(y to match.range).any { symbols.containsKey(it) }) {
                sum += match.value.toInt()
            }
        }
    }
    return sum
}

fun part2(input: List<String>): Long {
    val gearNumbers = symbolValue(input)
        .filterValues { it == '*' }
        .keys
        .associateBy({it}, {mutableSetOf<Int>()})

    val numRegex = "(\\d+)".toRegex()
    for (y in input.indices) {
        numRegex.findAll(input[y]).forEach { match ->
            adjacentValue(y to match.range)
                .filter { pair -> gearNumbers.containsKey(pair) }
                .forEach { pair -> gearNumbers[pair]!!.add(match.value.toInt()) }
        }
    }

    return gearNumbers.values
        .filter { it.size == 2 }
        .sumOf { nums -> nums.fold(1L) { mult, num -> num * mult } }
}

private fun symbolValue(scheme: List<String>): Map<Pair<Int, Int>, Char> {
    val symbols = mutableMapOf<Pair<Int, Int>, Char>()
    for (y in scheme.indices) {
        val chars = scheme[y].toCharArray()
        for (x in chars.indices) {
            val ch = chars[x]
            if (ch.isDigit() || ch == '.') continue
            symbols[y to x] = ch
        }
    }
    return symbols
}

private fun adjacentValue(pair: Pair<Int, IntRange>): Set<Pair<Int, Int>> {
    val adjacent = mutableSetOf<Pair<Int, Int>>()
    val (y, x) = pair
    for (dy in y-1..y+1) {
        for (dx in x.first -1..x.last +1) {
            adjacent.add(dy to dx)
        }
    }
    return adjacent
}