package com.github.juanmougan.advent.year2024

import com.github.juanmougan.advent.common.readLinesHavingTwoValues
import kotlin.math.abs

fun solution(fileName: String, delimiter: String): Int {
    val bothLists = readLinesHavingTwoValues(fileName, delimiter)
    val firstListSorted = bothLists.map { it.first }.map { it.toInt() }.sorted()
    val secondListSorted = bothLists.map { it.second }.map { it.toInt() }.sorted()
    return compareEachValue(firstListSorted, secondListSorted).reduce { a, b -> a + b }
}

fun compareEachValue(firstListSorted: List<Int>, secondListSorted: List<Int>): List<Int> {
    return firstListSorted.indices.map { i -> abs(firstListSorted[i] - secondListSorted[i]) }.toList()
}

fun List<Int>.similarityScore(number: Int): Int {
    // for 3 and [4, 3, 5, 3, 9, 3] it's 3 * 3 (there are three 3s on the list) = 9
    return this.count { it == number } * number
}

fun List<Int>.similarityScore(other: List<Int>): Int {
    return this.map { other.similarityScore(it) }.reduce { a, b -> a + b }
}

fun solutionSimilarityScore(fileName: String, delimiter: String): Int {
    val bothLists = readLinesHavingTwoValues(fileName, delimiter)
    val firstList = bothLists.map { it.first }.map { it.toInt() }
    val secondList = bothLists.map { it.second }.map { it.toInt() }
    return firstList.similarityScore(secondList)
}
