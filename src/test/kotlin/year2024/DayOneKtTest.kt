package year2024

import com.github.juanmougan.advent.year2024.solution
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
}
