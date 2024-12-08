package year2024

import com.github.juanmougan.advent.year2024.Equation
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class EquationTest : ShouldSpec({
    should("return true if an equation is valid") {
        val equation = Equation(result = 190, operands = listOf(10, 19))
        equation.isValid() shouldBe true
    }

    should("return false if an equation is not valid") {
        val equation = Equation(result = 21037, operands = listOf(9, 7, 18, 13))
        equation.isValid() shouldBe false
    }
})
