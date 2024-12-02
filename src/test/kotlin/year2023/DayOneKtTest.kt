package year2023

import com.github.juanmougan.advent.year2023.solution
import com.github.juanmougan.advent.year2023.textToDigit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DayOneKtTest {

    @Test
    fun `Should parse short file`(): Unit {
        // given sample input file
        val fileName = "year2023/dayOne/short_example.txt"
        // when calculate
        val result = solution(fileName)
        // then check
        Assertions.assertEquals(142, result)
    }

    @Test
    fun `Should parse long file`(): Unit {
        // given sample input file
        val fileName = "year2023/dayOne/full_example.txt"
        // when calculate
        val result = solution(fileName)
        // then check
        Assertions.assertEquals(55208, result)
    }

    @Test
    fun `Should replace text with digit`(): Unit {
        // given text with numbers as string
        val input = "onetwothree"
        // when replace then get digits
        Assertions.assertEquals("123", input.textToDigit())
    }

    @Test
    fun `Should parse short file with letters`(): Unit {
        // given sample input file
        println("\n\n\n\n\nWITH LETTERS!\n\n\n\n\n")
        val fileName = "year2023/dayOne/short_example_with_letters.txt"
        // when calculate
        val result = solution(fileName)
        // then check
        Assertions.assertEquals(281, result)
    }
}
