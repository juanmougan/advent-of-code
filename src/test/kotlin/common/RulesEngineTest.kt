package common

import com.github.juanmougan.advent.common.RulesEngine
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainExactly

class RulesEngineTest : ShouldSpec({
    should("parse a file to create the rules") {
        val rulesEngine = RulesEngine(rulesFileName = "year2024/dayFive/only_pairs_reduced.txt")
        rulesEngine.rules.shouldContainExactly(
            listOf(
                Pair(47, 53), Pair(97, 13), Pair(97, 61)
            )
        )
    }

    should("verify the order") {
        val rulesEngine = RulesEngine("year2024/dayFive/only_rules.txt")
        rulesEngine.firstElementIsBeforeSecond(first = 75, second = 29)
        rulesEngine.firstElementIsBeforeSecond(first = 75, second = 53)
        rulesEngine.firstElementIsBeforeSecond(first = 97, second = 75)
    }
})
