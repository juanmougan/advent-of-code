package com.github.juanmougan.advent.year2024

import com.github.juanmougan.advent.common.getFile
import com.github.juanmougan.advent.common.toMatrixWithoutDelimiter

fun findXmasInFile(fileName: String, text: String): Int {
    val matrix = getFile(fileName).toMatrixWithoutDelimiter()
    val sequence = text.toList().map { it.toString() }
    return matrix.findOccurrencesInRows(sequence) + matrix.findOccurrencesInColumns(sequence) + matrix.findOccurrencesDiagonally(
        sequence
    )
}
