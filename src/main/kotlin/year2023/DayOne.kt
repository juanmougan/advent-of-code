package com.github.juanmougan.advent.year2023

import com.github.juanmougan.advent.common.readLinesHavingSingleValue

val DIGIT_MATCHER = Regex("[0-9]")

fun solution(fileName: String): Int {
    val lines = readLinesHavingSingleValue(fileName)
    return lines
        .asSequence()
        .map { line -> line.textToDigit() }
        .map { line -> line.firstAndLastDigits() }
        .onEach { println("First: ${it.first}, last: ${it.second}") }   // TODO delete this, just for debbuging
        .map { it.toNumber() }.reduce { a, b -> a + b }
}

fun Pair<Int, Int>.toNumber(): Int {
    return this.first * 10 + second
}

fun String.firstAndLastDigits(): Pair<Int, Int> {
    val first = this.first { DIGIT_MATCHER.matches(it.toString()) }
    val last = this.last { DIGIT_MATCHER.matches(it.toString()) }
    return Pair(first = first.digitToInt(), second = last.digitToInt())
}

fun String.textToDigit(): String {
    val mapper = mapOf(
        "zero" to "0",
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )
    var output = this
    for ((key, value) in mapper) {
        output = output.replace(key, value)
    }
    return output
}
