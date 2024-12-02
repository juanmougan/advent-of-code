package year2024

import com.github.juanmougan.advent.year2024.similarityScore
import com.github.juanmougan.advent.year2024.solution
import com.github.juanmougan.advent.year2024.solutionSimilarityScore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

const val DELIMETER = "   "

class DayOneKtTest {

    @Test
    fun `Should process a short list`() {
        // given file name
        val fileName = "year2024/dayOne/short_list.txt"
        // when process then get the result
        Assertions.assertEquals(11, solution(fileName, DELIMETER))
    }

    @Test
    fun `Should process the full list`() {
        // given file name
        val fileName = "year2024/dayOne/full_list.txt"
        // when process then get the result
        Assertions.assertEquals(2378066, solution(fileName, DELIMETER))
    }

    @Test
    fun `Should return the similarity score for a list and a number`() {
        // given list and number
        val list = listOf(4, 3, 5, 3, 9, 3)
        // when process then get the score
        Assertions.assertEquals(9, list.similarityScore(3))
    }

    @Test
    fun `Should return zero as similarity score for a list and a number not present on it`() {
        // given list and number
        val list = listOf(4, 3, 5, 3, 9, 3)
        // when process then get the score
        Assertions.assertEquals(0, list.similarityScore(2))
    }

    @Test
    fun `Should calculate the similarity score for two lists`() {
        // given two lists inside a file
        val fileName = "year2024/dayOne/short_list.txt"
        // when calculate then return the score
        Assertions.assertEquals(31, solutionSimilarityScore(fileName, DELIMETER))
    }

    @Test
    fun `Should calculate the similarity score for two full lists`() {
        // given two lists inside a file
        val fileName = "year2024/dayOne/full_list.txt"
        // when calculate then return the score
        Assertions.assertEquals(18934359, solutionSimilarityScore(fileName, DELIMETER))
    }
}
