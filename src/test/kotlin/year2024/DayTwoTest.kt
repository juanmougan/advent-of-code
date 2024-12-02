package year2024

import com.github.juanmougan.advent.year2024.DayTwo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DayTwoTest {

    @Test
    fun `Should process small matrix and return safe records`() {
        // given small matrix
        val fileName = "year2024/dayTwo/small_matrix.txt"
        // when process then return safe count
        Assertions.assertEquals(2, DayTwo().firstSolution(fileName))
    }

    @Test
    fun `Should process full matrix and return safe records`() {
        // given small matrix
        val fileName = "year2024/dayTwo/full_matrix.txt"
        // when process then return safe count
        Assertions.assertEquals(442, DayTwo().firstSolution(fileName))
    }

    @Test
    fun `Should process small matrix with Problem Dampener and return safe records`() {
        // given small matrix
        val fileName = "year2024/dayTwo/small_matrix.txt"
        // when process then return safe count
        Assertions.assertEquals(4, DayTwo().secondSolution(fileName))
    }

    @Test
    fun `Should process full matrix with Problem Dampener and return safe records`() {
        // given small matrix
        val fileName = "year2024/dayTwo/full_matrix.txt"
        // when process then return safe count
        Assertions.assertEquals(493, DayTwo().secondSolution(fileName))
    }
}
