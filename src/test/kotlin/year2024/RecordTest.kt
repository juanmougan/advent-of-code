package year2024

import com.github.juanmougan.advent.year2024.Record
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RecordTest {

    @Test
    fun `Should be safe decreasing`() {
        val record = Record(listOf(7, 6, 4, 2, 1))
        Assertions.assertTrue(record.isSafe())
    }

    @Test
    fun `Should be unsafe because of threshold increase`() {
        val record = Record(listOf(1, 2, 7, 8, 9))
        Assertions.assertFalse(record.isSafe())
    }

    @Test
    fun `Should be unsafe because of threshold decrease`() {
        val record = Record(listOf(9, 7, 6, 2, 1))
        Assertions.assertFalse(record.isSafe())
    }

    @Test
    fun `Should be unsafe because of neither increasing nor decreasing`() {
        val record = Record(listOf(8, 6, 4, 4, 1))
        Assertions.assertFalse(record.isSafe())
    }

    @Test
    fun `Should be unsafe because of increasing and decreasing`() {
        val record = Record(listOf(1, 3, 2, 4, 5))
        Assertions.assertFalse(record.isSafe())
    }

    @Test
    fun `Should be unsafe because of increasing and decreasing, and repeated`() {
        val record = Record(listOf(64, 65, 67, 74, 75, 78, 79, 79))
        Assertions.assertFalse(record.isSafe())
    }

    @Test
    fun `Should be safe increasing`() {
        val record = Record(listOf(1, 3, 6, 7, 9))
        Assertions.assertTrue(record.isSafe())
    }

    @Test
    fun `Should be unsafe all equal`() {
        val record = Record(listOf(4, 4, 4, 4))
        Assertions.assertFalse(record.isSafe())
    }

    @Test
    fun `Should be unsafe with large gap before last one`() {
        val record = Record(listOf(9, 11, 13, 14, 17, 24))
        Assertions.assertFalse(record.isSafe())
    }
}
