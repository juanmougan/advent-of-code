package common

import com.github.juanmougan.advent.common.getFile
import com.github.juanmougan.advent.common.toMatrix
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe


// TODO get the files before each test
class MatrixTest : ShouldSpec({
    should("list all diagonals in the matrix") {
        val matrix = getFile("common/4x4_matrix_diagonal.txt").toMatrix()
        val diagonals = matrix.diagonals()
        diagonals shouldContainExactlyInAnyOrder listOf(
            listOf("X", "M", "A", "S"),
            listOf("D", "H", "L"),
            listOf("G", "K"),
            listOf("J"),
            listOf("A", "E", "I"),
            listOf("B", "F"),
            listOf("C"),
            listOf("C", "E", "H", "J"),
            listOf("F", "A", "K"),
            listOf("I", "L"),
            listOf("S"),
            listOf("B", "M", "G"),
            listOf("A", "D"),
            listOf("X")
        )
    }

    should("find a text present in a diagonal") {
        val matrix = getFile("common/4x4_matrix_diagonal.txt").toMatrix()
        matrix.findOccurrencesDiagonally(listOf("X", "M", "A", "S")) shouldBe 1
    }

    should("find a text present in a diagonal backwards") {
        val matrix = getFile("common/4x4_matrix_diagonal_reversed.txt").toMatrix()
        matrix.findOccurrencesDiagonally(listOf("X", "M", "A", "S")) shouldBe 1
    }

    should("return all rows") {
        val matrix = getFile("common/4x4_matrix_diagonal.txt").toMatrix()
        matrix.rows() shouldContainExactly listOf(
            "X A B C".split(" "), "D M E F".split(" "), "G H A I".split(" "), "J K L S".split(" ")
        )
    }

    should("return all columns") {
        val matrix = getFile("common/4x4_matrix_diagonal.txt").toMatrix()
        matrix.columns() shouldContainExactly listOf(
            "X D G J".split(" "), "A M H K".split(" "), "B E A L".split(" "), "C F I S".split(" ")
        )
    }

    should("find a text present in a column") {
        val matrix = getFile("common/4x4_matrix_column.txt").toMatrix()
        matrix.findOccurrencesInColumns(listOf("X", "M", "A", "S")) shouldBe 1
    }

    should("find a text present in a column reversed") {
        val matrix = getFile("common/4x4_matrix_column_reversed.txt").toMatrix()
        matrix.findOccurrencesInColumns(listOf("X", "M", "A", "S")) shouldBe 1
    }

    should("find a text present in a row") {
        val matrix = getFile("common/4x4_matrix_row.txt").toMatrix()
        matrix.findOccurrencesInRows(listOf("X", "M", "A", "S")) shouldBe 1
    }

    should("find a text present in a row reversed") {
        val matrix = getFile("common/4x4_matrix_row_reversed.txt").toMatrix()
        matrix.findOccurrencesInRows(listOf("X", "M", "A", "S")) shouldBe 1
    }
})
