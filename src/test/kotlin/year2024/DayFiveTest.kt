package year2024

import com.github.juanmougan.advent.common.RulesEngine
import com.github.juanmougan.advent.year2024.DayFive
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class DayFiveTest : ShouldSpec({
    should("sum the valid middle elements") {
        val day = DayFive(RulesEngine(rulesFileName = "year2024/dayFive/only_rules.txt"))
        val result = day.dayFivePartOneSolution(updatesFileName = "year2024/dayFive/only_updates.txt")
        println(result)
        result shouldBe 143
    }

    should("sum the valid middle elements for full files") {
        val day = DayFive(RulesEngine(rulesFileName = "year2024/dayFive/full_rules_only.txt"))
        val result = day.dayFivePartOneSolution(updatesFileName = "year2024/dayFive/full_updates_only.txt")
        println(result)
        result shouldBe 143
    }
})
