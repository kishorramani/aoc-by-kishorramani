package day2

import readInputDay2

fun main() {
    val testInput = readInputDay2("Day02_test")

    println("part 1: ${part1(testInput, redLimit = 12, greenLimit = 13, blueLimit = 14)}")
    println("part 2: ${part2(testInput)}")
}

fun part1(input: List<String>, redLimit: Int, greenLimit: Int, blueLimit: Int): Int {
    val listOfCubeGame = input.map { line ->
        CubeGame.fromString(line)
    }
    val validListOfCubeGame = listOfCubeGame.filter { game ->
        game.red.all {
            it <= redLimit
        } && game.green.all {
            it <= greenLimit
        } && game.blue.all {
            it <= blueLimit
        }
    }
    val sum = validListOfCubeGame.sumOf { game ->
        game.id
    }
    return sum
}

fun part2(input: List<String>): Int {
    val listOfCubeGame = input.map { line ->
        CubeGame.fromString(line)
    }
    val sum = listOfCubeGame.sumOf { game ->
        game.red.max() * game.green.max() * game.blue.max()
    }
    return sum
}

class CubeGame(val id: Int, val red: IntArray, val green: IntArray, val blue: IntArray) {
    companion object {
        fun fromString(game: String): CubeGame {
            val gameRegex = "^Game (\\d+): (.*)$".toRegex()
            val (gameId, gameData) = gameRegex.find(game)!!.destructured
            //println("gameId: $gameId , gameData: gameData")
            val gameResults = gameData.split(';')
            val red = IntArray(gameResults.size)
            val green = IntArray(gameResults.size)
            val blue = IntArray(gameResults.size)
            for ((i, result) in gameResults.withIndex()) {
                red[i] = "(\\d+) red".toRegex().find(result)?.groupValues?.get(1)?.toInt() ?: 0
                green[i] = "(\\d+) green".toRegex().find(result)?.groupValues?.get(1)?.toInt() ?: 0
                blue[i] = "(\\d+) blue".toRegex().find(result)?.groupValues?.get(1)?.toInt() ?: 0
            }
            //println("gameId: $gameId")
            //println("red: ${red.asList()}")
            //println("green: ${green.asList()}")
            //println("blue: ${blue.asList()}")
            return CubeGame(gameId.toInt(), red = red, green = green, blue = blue)
        }
    }
}