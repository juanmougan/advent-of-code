package year2024

import com.github.juanmougan.advent.year2024.DaySeven
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DaySevenTest : ShouldSpec({
    should("solve a small list") {
        val result = DaySeven().solve("year2024/daySeven/small_list.txt")
        result shouldBe 3749
    }

    should("solve a large list") {
        val result = DaySeven().solve("year2024/daySeven/large_list.txt")
        result shouldBe 5702958180383
    }
})
