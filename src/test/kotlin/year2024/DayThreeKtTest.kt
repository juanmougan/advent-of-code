package year2024

import com.github.juanmougan.advent.year2024.filterCorruptedInput
import com.github.juanmougan.advent.year2024.thirdDayFirstSolution
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayThreeKtTest {

    @Test
    fun `Should filter short input`() {
        val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val result = input.filterCorruptedInput()
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
}
