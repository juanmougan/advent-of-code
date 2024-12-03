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
const val BASIC_FILTER_PATTERN = "mul\\(\\d{1,3},\\d{1,3}\\)"

/*
Regex explanation:
    - do\(\) to match "do()"
    - | is the OR operator
    - don\'t\(\) to match "don't()"
    - | is the OR operator again
    - Rest of the basic pattern
 */
const val EXTENDED_FILTER_PATTERN = "do\\(\\)|don\\'t\\(\\)|$BASIC_FILTER_PATTERN"

const val ARGUMENTS_PATTERN = "\\d{1,3},\\d{1,3}"

fun String.filterCorruptedInput(pattern: String): List<String> {
    val regex = pattern.toRegex()
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
    return filteredInput.asSequence().map { it.filterCorruptedInput(BASIC_FILTER_PATTERN) }
        .map { operations -> operations.map { operation -> operation.getOperands() } }.flatten()
        .map { it.first * it.second }.reduce(Int::plus)
}

private const val RESUME_OPERATOR = "do()"

private const val PAUSE_OPERATOR = "don't()"

data class Program(var input: List<String>, var isActive: Boolean) {
    fun removeInactiveStatements() {
        val filtered = this.input.asSequence().map { discardIfInactive(it) }.filter { it.isNotBlank() }.toList()
        this.input = filtered
    }

    private fun discardIfInactive(item: String): String {
        if (item == PAUSE_OPERATOR) {
            this.isActive = false
        }
        if (item == RESUME_OPERATOR) {
            this.isActive = true
        }
        return when {
            this.isActive && item != RESUME_OPERATOR -> item
            else -> ""
        }
    }
}

fun thirdDaySecondSolution(fileName: String): Int {
    val input = readLines(fileName)
    val nonCorruptStatements =
        input.asSequence().map { it.filterCorruptedInput(EXTENDED_FILTER_PATTERN) }.flatten().toList()
    val program = Program(nonCorruptStatements, true)
    program.removeInactiveStatements()
    return program.input.map { operation -> operation.getOperands() }.map { it.first * it.second }.reduce(Int::plus)
}
