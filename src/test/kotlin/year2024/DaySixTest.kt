package year2024

import com.github.juanmougan.advent.year2024.DaySix
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class DaySixTest : ShouldSpec({
    should("return the movements from a small matrix") {
        DaySix().solve("year2024/daySix/small_matrix.txt")
    }

})
