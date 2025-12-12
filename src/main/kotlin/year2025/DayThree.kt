package com.github.juanmougan.advent.year2025

import java.io.File

class DayThree {
    fun findLargestDigits(arr: IntArray): Int {
        val allPairs = mutableListOf<Int>()

        for (i in arr.indices) {
            for (j in (i + 1) until arr.size) {
                val twoDigitNumber = arr[i] * 10 + arr[j]
                allPairs.add(twoDigitNumber)
            }
        }

        return allPairs.maxOrNull() ?: 0        // TODO maybe I can live with max() and an Exception here...
    }

    fun parseInput(fileName: String): List<IntArray> {
        return File(fileName)
            .readLines()
            .map { line ->
                line.map { it.digitToInt() }.toIntArray()
            }
    }
}

fun main() {
    val dayThree = DayThree()
    val input = dayThree.parseInput("/Users/jmougan/code/personal/advent/src/main/resources/year2025/day3/input.txt")
    println("Total joltage: ${input.sumOf { dayThree.findLargestDigits(it) }}")

//    val input = dayThree.parseInput("/Users/jmougan/code/personal/advent/src/main/resources/year2025/day3/inputReduced.txt")
//    input.map { dayThree.findLargestDigits(it) }.forEach { println(it) }
    // should print 98, 89, 78, 92
}
