package com.github.juanmougan.advent.common

import java.io.File

// Shameless ChatGPT generated snippet
class Matrix<T>(private val data: List<List<T>>) {
    init {
        require(data.isNotEmpty()) { "Matrix cannot be empty" }
        val columnCount = data.first().size
        require(data.all { it.size == columnCount }) { "All rows must have the same number of columns" }
    }

    val rows: Int get() = data.size
    val columns: Int get() = data.first().size

    operator fun get(row: Int, col: Int): T {
        require(row in 0 until rows && col in 0 until columns) { "Index out of bounds" }
        return data[row][col]
    }

    fun row(rowIndex: Int): List<T> {
        require(rowIndex in 0 until rows) { "Row index out of bounds" }
        return data[rowIndex]
    }

    fun column(colIndex: Int): List<T> {
        require(colIndex in 0 until columns) { "Column index out of bounds" }
        return data.map { it[colIndex] }
    }

    fun rows(): List<List<T>> {
        val allRows = mutableListOf<List<T>>()
        repeat(rows) { rowIndex -> allRows.add(row(rowIndex)) }
        return allRows
    }

    fun <T> findOccurrencesInRows(sequence: List<T>): Int {
        return rows().sumOf { row ->
            row.windowed(sequence.size).count { it == sequence || it == sequence.asReversed() }
        }
    }

    fun columns(): List<List<T>> {
        val allColumns = mutableListOf<List<T>>()
        repeat(columns) { columnIndex -> allColumns.add(column(columnIndex)) }
        return allColumns
    }

    fun <T> findOccurrencesInColumns(sequence: List<T>): Int {
        return columns().sumOf { column ->
            column.windowed(sequence.size).count { it == sequence || it == sequence.asReversed() }
        }
    }

    fun mainDiagonal(): List<T> {
        val minDimension = minOf(rows, columns)
        return List(minDimension) { index -> data[index][index] }
    }

    fun antiDiagonal(): List<T> {
        val minDimension = minOf(rows, columns)
        return List(minDimension) { index -> data[index][columns - 1 - index] }
    }

    fun diagonals(): List<List<T>> {
        val diagonals = mutableListOf<List<T>>()

        // Top-left to bottom-right diagonals
        for (i in 0 until rows) diagonals.add(extractDiagonal(i, 0, true))
        for (j in 1 until columns) diagonals.add(extractDiagonal(0, j, true))

        // Top-right to bottom-left diagonals
        for (i in 0 until rows) diagonals.add(extractDiagonal(i, columns - 1, false))
        for (j in columns - 2 downTo 0) diagonals.add(extractDiagonal(0, j, false))

        return diagonals
    }

    private fun extractDiagonal(startRow: Int, startCol: Int, isMain: Boolean): List<T> {
        val diagonal = mutableListOf<T>()
        var row = startRow
        var col = startCol

        while (row in 0 until rows && col in 0 until columns) {
            diagonal.add(data[row][col])
            row += 1
            col += if (isMain) 1 else -1
        }
        return diagonal
    }

    fun <T> findOccurrencesDiagonally(sequence: List<T>): Int {
        return diagonals().sumOf { diagonal ->
            diagonal.windowed(sequence.size).count { it == sequence || it == sequence.asReversed() }
        }
    }

//    fun <T> findAllDiagonally(sequence: List<T>): Int {
//        val diagonals = diagonals()
//        return diagonals.windowed(sequence.size).map { it.hasMatchingPerpendicularDiagonal(sequence) }.count()
//    }

    // TODO for each diagonal retrieved, find the perpendicular diagonal. They should contain the same value, the sequence
//    private fun <T> List<List<T>>.hasMatchingPerpendicularDiagonal(sequence: List<T>): List<List<T>> {
//        val diagonal = this.filter { diagonal -> diagonal == sequence }
//        val diagonalReversed = this.filter { diagonal -> diagonal == sequence.asReversed() }
//        return diagonal.middleElement()
//    }
//
//    fun <T> List<T>.middleElement(): T {
//        if (this.size.mod(2) == 0) throw IllegalArgumentException("List must be odd in order to have a middle element")
//        return this.get(this.size / 2)
//    }
}

fun <T> Matrix<T>.rotateClockwise(): Matrix<T> {
    // Transpose the matrix
    val transposed = (0 until this.columns).map { colIndex ->
        (0 until this.rows).map { rowIndex -> this[rowIndex, colIndex] }
    }
    // Reverse the rows of the transposed matrix to complete the clockwise rotation
    val rotatedData = transposed.map { it.reversed() }
    return Matrix(rotatedData)
}

fun File.toMatrix(delimiter: String = " "): Matrix<String> {
    val lines = this.readLines()
    val matrixData = lines.map { line -> line.split(delimiter) }
    return Matrix(matrixData)
}

fun File.toMatrixWithoutDelimiter(): Matrix<String> {
    val lines = this.readLines()
    val matrixData = lines.map { line -> line.toList() }
        .map { chars -> chars.map { char -> char.toString().replace("[^A-Za-z ]".toRegex(), "") } }
    return Matrix(matrixData)
}
