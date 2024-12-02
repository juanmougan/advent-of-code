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
