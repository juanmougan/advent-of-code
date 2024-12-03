package com.github.juanmougan.advent.year2024

import com.github.juanmougan.advent.common.readLines

/*
Regex explanation:
    - mul to match "mul"
    - \( to match one opening parenthesis
    - \d{1,3} to match up to three digits
    - , to match the separating comma
    - \d{1,3} to match up to three digits
    - \) to match one closing parenthesis
*/
const val FILTER_PATTERN = "mul\\(\\d{1,3},\\d{1,3}\\)"

const val ARGUMENTS_PATTERN = "\\d{1,3},\\d{1,3}"

fun String.filterCorruptedInput(): List<String> {
    val regex = FILTER_PATTERN.toRegex()
    return regex.findAll(this).map { it.value }.toList()
}

private fun String.getOperands(): Pair<Int, Int> {
    val regex = ARGUMENTS_PATTERN.toRegex()
    val matches = regex.find(this)!!
    val rawOperands = matches.value.split(",")
    return Pair(rawOperands[0].toInt(), rawOperands[1].toInt())
}

fun thirdDayFirstSolution(fileName: String): Int {
    val filteredInput = readLines(fileName)
    return filteredInput.asSequence().map { it.filterCorruptedInput() }
        .map { operations -> operations.map { operation -> operation.getOperands() } }.flatten()
        .map { it.first * it.second }.reduce(Int::plus)
}
