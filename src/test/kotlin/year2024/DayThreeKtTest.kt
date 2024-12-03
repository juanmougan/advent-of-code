package year2024

import com.github.juanmougan.advent.year2024.BASIC_FILTER_PATTERN
import com.github.juanmougan.advent.year2024.EXTENDED_FILTER_PATTERN
import com.github.juanmougan.advent.year2024.filterCorruptedInput
import com.github.juanmougan.advent.year2024.thirdDayFirstSolution
import com.github.juanmougan.advent.year2024.thirdDaySecondSolution
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DayThreeKtTest {

    @Test
    fun `Should filter short input`() {
        val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val result = input.filterCorruptedInput(BASIC_FILTER_PATTERN)
        assertEquals("mul(2,4)", result[0])
        assertEquals("mul(5,5)", result[1])
        assertEquals("mul(11,8)", result[2])
        assertEquals("mul(8,5)", result[3])
    }

    @Test
    fun `Should process short input`() {
        val fileName = "year2024/dayThree/short_input.txt"
        val result = thirdDayFirstSolution(fileName)
        assertEquals(161, result)
    }

    @Test
    fun `Should process full input`() {
        val fileName = "year2024/dayThree/full_input.txt"
        val result = thirdDayFirstSolution(fileName)
        assertEquals(160672468, result)
    }

    // TODO I need something like assertJ here
    @Test
    fun `Should filter short input for extended pattern`() {
        val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        val result = input.filterCorruptedInput(EXTENDED_FILTER_PATTERN)
        assertEquals("mul(2,4)", result[0])
        assertEquals("don't()", result[1])
        assertEquals("mul(5,5)", result[2])
        assertEquals("mul(11,8)", result[3])
        assertEquals("do()", result[4])
        assertEquals("mul(8,5)", result[5])
    }

    @Test
    fun `Should process short input for scenario 2`() {
        val fileName = "year2024/dayThree/part_two_short_input.txt"
        assertEquals(48, thirdDaySecondSolution(fileName))
    }

    @Test
    fun `Should process full input for scenario 2`() {
        val fileName = "year2024/dayThree/full_input.txt"
        assertEquals(84893551, thirdDaySecondSolution(fileName))
    }
}
