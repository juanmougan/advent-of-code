package com.github.juanmougan.advent.year2025

import com.github.juanmougan.advent.common.getFile

data class Input(val rangeStart: Long, val rangeEnd: Long, val freshIngredients: Set<Long>)

class DayFive {
    fun parseInputFile(filePath: String): Input {
        val file = getFile(filePath)
        val statuses = mutableSetOf<Long>()
        var reachedRangeStart = false
        var lowestRange = Long.MAX_VALUE    // Doesn't really matter
        var highestRange = 0L               // Doesn't really matter
        var alreadyPastFirstRangeLine = false
        file.readLines().forEach { line ->
            if (line.isEmpty()) {
                println("Finished with the freshness, starting with the ranges")
                reachedRangeStart = true
            } else {
                if (reachedRangeStart) {
                    if (alreadyPastFirstRangeLine) {
                        highestRange = line.toLong()
                    } else {
                        lowestRange = line.toLong()
                        alreadyPastFirstRangeLine = true
                    }
                } else {
                    parseFreshness(line, statuses)
                }
            }
        }
        return Input(rangeStart = lowestRange, rangeEnd = highestRange, freshIngredients = statuses)
    }

    fun solveInputFileTwoPointers(filePath: String): Int {
        val file = getFile(filePath)
        var counter = 0

        outerLoop@ for (line in file.readLines()) {
            if (line.split("-").size == 2 || line.isEmpty()) {
                // Discard all lines till the blank one
                continue@outerLoop
            }
            // Now `line` points to the first line in the range to evaluate
            val id = line.toLong()
            val innerPointer = getFile(filePath)
            innerLoop@ for (innerLine in innerPointer.readLines()) {
                if (innerLine.isEmpty()) {
                    // Reached the blank line again - not found. Move on to the next number
                    continue@outerLoop
                }
                val (startId, endId) = innerLine.split("-").map { it.toLong() }
                if (id in startId..endId) {
                    counter++
                    continue@outerLoop
                }
            }
        }

        return counter
    }

    // TODO delete
    private fun parseRangeToEvaluate(line: String, lowestRange: Long, highestRange: Long): Pair<Long, Long> {
        val num = line.toLong()
        var tmpLowestRange = 0L
        var tmpHighestRange = 0L
        if (num < lowestRange) {
            tmpLowestRange = lowestRange
        }
        if (num > highestRange) {
            tmpHighestRange = highestRange
        }
        return Pair(tmpLowestRange, tmpHighestRange)
    }

    private fun parseFreshness(line: String, statuses: MutableSet<Long>) {
        val startEnd = line.split("-").map { it.toLong() }
        for (id in startEnd[0]..startEnd[1]) {
            statuses.add(id)
        }
    }

    /**
     * This won't work for a very large input
     */
    fun solvePart1(inputFileName: String): Int {
        val result = parseInputFile(inputFileName)
        var count = 0
        // TODO maybe internal iteration instead?
        for (ingredient in (result.rangeStart)..(result.rangeEnd)) {
            if (result.freshIngredients.contains(ingredient)) {
                count++
            }
        }
        return count
    }
}
