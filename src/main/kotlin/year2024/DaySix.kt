package com.github.juanmougan.advent.year2024

import com.github.juanmougan.advent.common.readLines

const val INPUT_WIDTH = 130
const val INPUT_HEIGHT = 130

enum class Cell(val type: Char) {
    GUARD_NORTH('^'), GUARD_EAST('>'), GUARD_SOUTH('v'), GUARD_WEST('<'), OBSTACLE('#'), EMPTY('.'), OUTSIDE('-');

    companion object {
        fun fromChar(c: Char): Cell {
            return when (c) {
                '^' -> GUARD_NORTH
                '>' -> GUARD_EAST
                'v' -> GUARD_SOUTH
                '<' -> GUARD_WEST
                '#' -> OBSTACLE
                '.' -> EMPTY
                '-' -> OUTSIDE
                else -> throw IllegalArgumentException("Invalid cell type: $c")
            }
        }
    }

    fun isBusy(): Boolean {
        return this == OBSTACLE
    }
}

// TODO maybe use arrays instead?
class Board(fileName: String) {
    fun moveGuard() {
        // TODO better abstractions here, so to delegate logic to a new Whatever class
        val position = this.cells[guardPosition.first][guardPosition.second]
        when (position) {
            Cell.GUARD_NORTH -> processMoveNorth()
            Cell.GUARD_SOUTH -> getNextSouthPosition()
            Cell.GUARD_EAST -> getNextEastPosition()
            Cell.GUARD_WEST -> getNextWestPosition()
            else -> return
        }
    }

    private fun getNextWestPosition() {
        TODO("Not yet implemented")
    }

    private fun getNextEastPosition() {
        TODO("Not yet implemented")
    }

    private fun getNextSouthPosition() {
        TODO("Not yet implemented")
    }

    private fun processMoveNorth() {
        val nextPosition = Pair(this.guardPosition.first + 1, this.guardPosition.second)
        cells[this.guardPosition.first][this.guardPosition.second] = Cell.EMPTY      // or... previous value? I need to store it
        processNextPosition(nextPosition)
    }

    private fun processNextPosition(nextPosition: Pair<Int, Int>) {
        if (cells[nextPosition.first][nextPosition.second].isBusy()) {

        }
    }

    // First, all empty values
    private var cells: Array<Array<Cell>> = Array(INPUT_WIDTH + 2, {
        Array(INPUT_HEIGHT + 2, { e -> Cell.OUTSIDE })
    })

    var guardPosition = Pair(0, 0)

    init {
        // Then, load from the file
        val lines = readLines(fileName)
        val chars = lines.map { line -> line.toList() }
        chars.forEachIndexed { i, row ->
            row.forEachIndexed { j, char ->
                val cell = Cell.fromChar(char)
                cells[i + 1][j + 1] = cell
                if (cell == Cell.GUARD_NORTH) {
                    guardPosition = Pair(i + 1, j + 1)
                }
            }
        }
    }
}

fun Pair<Int, Int>.inside(): Boolean {
    return first in 1 until INPUT_WIDTH && second in 1 until INPUT_HEIGHT
}

class DaySix {
    fun solve(fileName: String): Int {
        val board = Board(fileName)
        // Hacking a bit here, I know the guard starts facing NORTH
        while (board.guardPosition.inside()) {
            board.moveGuard()
        }
        return 42
    }
}
