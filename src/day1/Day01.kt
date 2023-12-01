package day1

import readInputDay1

fun main() {
    val testInput = readInputDay1("Day01_test")

    val sumOfPart1 = testInput.sumOf { getCalibrationValuesPartOne(it) }
    println("Part1: Sum of all Calibration Values is: $sumOfPart1")

    val sumOfPart2 = testInput.sumOf { getCalibrationValuesPartTwo(it) }
    println("Part2: Sum of all Calibration Values is: $sumOfPart2")
}

fun getCalibrationValuesPartOne(data: String): Int {
    val numbers = data.filter { it.isDigit() }
    val result: Int = if (numbers.length >= 2) {
        val firstDigit = numbers[0].toString().toInt()
        val lastDigit = numbers[numbers.length - 1].toString().toInt()
        (firstDigit * 10) + lastDigit
    } else {
        (numbers.toInt() * 10) + numbers.toInt()
    }
    return result
}

fun getCalibrationValuesPartTwo(data: String): Int {
    val validData = listOf(
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9"
    )
    val firstDigit = data.findAnyOf(validData)!!.second.readAsDigit().toInt()
    val lastDigit = data.findLastAnyOf(validData)!!.second.readAsDigit().toInt()

    return (firstDigit * 10) + lastDigit
}

fun String.readAsDigit(): String = when (this) {
    "one" -> "1"
    "two" -> "2"
    "three" -> "3"
    "four" -> "4"
    "five" -> "5"
    "six" -> "6"
    "seven" -> "7"
    "eight" -> "8"
    "nine" -> "9"
    else -> this
}