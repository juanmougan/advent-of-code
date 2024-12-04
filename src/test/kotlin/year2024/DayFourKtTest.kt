package year2024

import com.github.juanmougan.advent.common.getFile
import com.github.juanmougan.advent.common.toMatrix
import com.github.juanmougan.advent.year2024.findXmasInFile
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe


class DayFourKtTest : ShouldSpec({
    should("find a pattern in a small matrix") {
        findXmasInFile("year2024/dayFour/small_matrix_with_dots.txt", "XMAS") shouldBe 18
    }

    should("find a pattern in a full matrix") {
        findXmasInFile("year2024/dayFour/full_matrix.txt", "XMAS") shouldBe 18
    }
})
