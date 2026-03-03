package year2025

import com.github.juanmougan.advent.year2025.DayFive
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DayFiveTest {

    private val dayFive: DayFive = DayFive()

    @Test
    fun `should parse input file and return a list of status and a list of elements`() {
        // Given a file path, when parse
        val result = dayFive.parseInputFile("year2025/day5/minimal_input.txt")
        // Then return two lists
        result.freshIngredients shouldBe setOf(3L, 4L, 5L)
        result.rangeStart shouldBe 1
        result.rangeEnd shouldBe 6
    }

    @Test
    fun `should solve part 1`() {
        // Given a short input file
        val input = "year2025/day5/minimal_input.txt"
        // When solved, then return the correct value
        dayFive.solvePart1(input) shouldBe 3
    }

    @Test
    fun `should solve part 1 two pointers minimal`() {
        // Given a short input file
        val input = "year2025/day5/minimal_input.txt"
        // When solved, then return the correct value
        dayFive.solveInputFileTwoPointers(input) shouldBe 1
    }

    @Test
    fun `should solve part 1 two pointers short`() {
        // Given a short input file
        val input = "year2025/day5/short_input.txt"
        // When solved, then return the correct value
        dayFive.solveInputFileTwoPointers(input) shouldBe 3
    }

    @Test
    fun `solve part 1 two pointers`() {
        val input = "year2025/day5/input.txt"
        println(dayFive.solveInputFileTwoPointers(input))
    }
}
