package com.github.juanmougan.advent.year2024

import com.github.juanmougan.advent.common.readLines
import kotlin.math.abs

const val SAFETY_THRESHOLD = 3

class DayTwo {
    fun firstSolution(fileName: String): Int {
        val records = readLines(fileName).map { it.toRecord() }
        return records.map { record -> record.isSafe() }.count { it }
    }
}

data class Record(val values: List<Int>) {
    fun isSafe(): Boolean {
        var start = 0
        var middle = 1
        var end = 2
        while (end < this.values.size) {
            val isWithinThreshold = isWithinThreshold(this.values[start], this.values[middle]) && isWithinThreshold(
                this.values[middle], this.values[end]
            )
            val isIncreasingOrDecreasing =
                isIncreasingOrDecreasing(this.values[start], this.values[middle], this.values[end])
            if (!isWithinThreshold || !isIncreasingOrDecreasing) return false
            start++
            middle++
            end++
        }
        return true
    }

    private fun isIncreasingOrDecreasing(startValue: Int, middleValue: Int, endValue: Int): Boolean {
        val firstCheckIncreasing = startValue < middleValue
        val secondCheckIncreasing = middleValue < endValue
        val alwaysIncreasing = firstCheckIncreasing && secondCheckIncreasing
        val alwaysDecreasing = !firstCheckIncreasing && !secondCheckIncreasing
        val allThreeDifferent = startValue != middleValue && middleValue != endValue
        return (alwaysIncreasing || alwaysDecreasing) && allThreeDifferent
    }

    private fun isWithinThreshold(startValue: Int, endValue: Int): Boolean {
        return abs(startValue - endValue) <= SAFETY_THRESHOLD
    }

    override fun toString(): String {
        return this.values.joinToString(" ")
    }
}

fun List<String>.toRecord(): Record {
    return Record(values = this.map { it.toInt() })
}