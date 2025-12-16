package com.github.juanmougan.advent.year2025

import java.io.File

/**
 * Represents the type of cell in the grid.
 */
enum class Cell(val symbol: Char) {
    EMPTY('.'),
    ROLL('@'),
    PADDING(' ');

    companion object {
        /**
         * Converts a character to a Cell type.
         * @throws IllegalArgumentException if the character is not a valid cell symbol
         */
        fun fromChar(char: Char): Cell {
            return when (char) {
                '.' -> EMPTY
                '@' -> ROLL
                else -> throw IllegalArgumentException("Invalid cell character: '$char'. Expected '.' or '@'.")
            }
        }
    }
}

/**
 * Represents a 2D grid of cells with padding.
 */
class Grid private constructor(
    private val cells: Array<Array<Cell>>
) {
    val width: Int = cells[0].size
    val height: Int = cells.size

    /**
     * Gets the cell at the specified position.
     * @param row The row index (0-based, including padding)
     * @param col The column index (0-based, including padding)
     */
    operator fun get(row: Int, col: Int): Cell {
        require(row in 0 until height) { "Row index $row out of bounds [0, $height)" }
        require(col in 0 until width) { "Column index $col out of bounds [0, $width)" }
        return cells[row][col]
    }

    /**
     * Prints the grid to the console.
     */
    fun print() {
        cells.forEach { row ->
            println(row.joinToString("") { it.symbol.toString() })
        }
    }

    /**
     * Counts the number of accessible ROLL cells in the grid.
     * A ROLL cell is accessible if it has less than 4 adjacent ROLL cells.
     * Adjacent cells include all 8 directions (including diagonals).
     * Only the non-padded portion of the grid is checked.
     */
    fun countAccessibleRolls(): Int {
        var accessibleCount = 0

        // Iterate over non-padded cells (skip first and last row/column)
        for (row in 1 until height - 1) {
            for (col in 1 until width - 1) {
                // Only check ROLL cells
                if (cells[row][col] == Cell.ROLL) {
                    // Count adjacent ROLL cells
                    val adjacentRolls = countAdjacentRolls(row, col)

                    // If less than 4 adjacent rolls, this cell is accessible
                    if (adjacentRolls < 4) {
                        accessibleCount++
                    }
                }
            }
        }

        return accessibleCount
    }

    /**
     * Counts the number of ROLL cells adjacent to the given position.
     * Checks all 8 directions including diagonals.
     */
    private fun countAdjacentRolls(row: Int, col: Int): Int {
        var count = 0

        // Check all 8 adjacent positions
        for (dRow in -1..1) {
            for (dCol in -1..1) {
                // Skip the center cell (the cell itself)
                if (dRow == 0 && dCol == 0) continue

                val adjacentRow = row + dRow
                val adjacentCol = col + dCol

                // Check if the adjacent cell is a ROLL
                if (cells[adjacentRow][adjacentCol] == Cell.ROLL) {
                    count++
                }
            }
        }

        return count
    }

    fun removeAllPossibleRolls(): Int {
        var accessibleRows = countAccessibleRolls()
        var countRemoved = 0
        while (accessibleRows > 0) {
            for (row in 1 until height - 1) {
                for (col in 1 until width - 1) {
                    // Only check ROLL cells
                    if (cells[row][col] == Cell.ROLL) {
                        val isAccessible = countAdjacentRolls(row, col) < 4
                        if (isAccessible) {
                            cells[row][col] = Cell.EMPTY    // Mark as empty for future iterations
                            countRemoved++
                        }
                    }
                }
            }
           accessibleRows = countAccessibleRolls()
        }
        return countRemoved
    }

    companion object {
        /**
         * Reads a grid from a file and adds padding around it.
         * @param filename The path to the file to read
         * @return A Grid object with padding
         * @throws IllegalArgumentException if any character in the file is invalid
         */
        fun fromFile(filename: String): Grid {
            val lines = File(filename).readLines()
                .filter { it.isNotBlank() } // Remove empty lines

            if (lines.isEmpty()) {
                throw IllegalArgumentException("File is empty or contains no valid data")
            }

            // Parse the file content
            val innerGrid = lines.map { line ->
                line.map { char -> Cell.fromChar(char) }.toTypedArray()
            }.toTypedArray()

            // Validate that all rows have the same length
            val innerWidth = innerGrid[0].size
            innerGrid.forEach { row ->
                if (row.size != innerWidth) {
                    throw IllegalArgumentException("All rows must have the same length")
                }
            }

            val innerHeight = innerGrid.size

            // Create padded grid: add 1 row top/bottom, 1 column left/right
            val paddedWidth = innerWidth + 2
            val paddedHeight = innerHeight + 2

            val paddedCells = Array(paddedHeight) { row ->
                Array(paddedWidth) { col ->
                    when {
                        // Top or bottom padding row
                        row == 0 || row == paddedHeight - 1 -> Cell.PADDING
                        // Left or right padding column
                        col == 0 || col == paddedWidth - 1 -> Cell.PADDING
                        // Inner cell (adjust indices for the padding offset)
                        else -> innerGrid[row - 1][col - 1]
                    }
                }
            }

            return Grid(paddedCells)
        }
    }
}

/**
 * Example usage
 */
fun exampleParsing() {
    // Create a sample file
    val testFile = File("test_grid.txt")
    testFile.writeText("""
        .@..@
        @....
        ..@.@
        @....
    """.trimIndent())

    println("Reading grid from file...")
    val grid = Grid.fromFile("test_grid.txt")

    println("\nGrid dimensions: ${grid.width} x ${grid.height}")
    println("\nGrid with padding (space character represents padding):")
    grid.print()

    println("\nAccessing cells:")
    println("Cell at (0,0): ${grid[0, 0]} (should be PADDING)")
    println("Cell at (1,1): ${grid[1, 1]} (first actual cell)")
    println("Cell at (1,2): ${grid[1, 2]} (second cell of first row)")

    // Cleanup
    testFile.delete()
}

fun main() {
    val grid = Grid.fromFile("src/main/resources/year2025/day4/input.txt")
    println("Accessible rolls: ${grid.countAccessibleRolls()}")
    println("Rolls removed: ${grid.removeAllPossibleRolls()}")
}
