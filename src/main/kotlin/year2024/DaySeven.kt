package com.github.juanmougan.advent.year2024

import com.github.juanmougan.advent.common.readLines
import com.github.juanmougan.advent.common.toEquation

class DaySeven {
    fun solve(fileName: String): Long {
        val equations = readLines(fileName).map { it.toEquation() }
        return equations.filter { it.isValid() }.map { it.result }.reduce { acc, equation -> acc + equation }
    }
}

class Equation(val result: Long, private val operands: List<Long>) {

    companion object {
        val OPERATORS = listOf("+", "*")
    }

    fun isValid(): Boolean {
        // Generate combinations of operators
        val operatorCombinations = generateCombinations(OPERATORS, operands.size - 1)
        // Build all expressions
        val allCombinations = mutableSetOf<String>()
        operatorCombinations.forEach { ops ->
            val expression = buildExpression(operands, ops)
            allCombinations.add(expression)
        }
        return allCombinations.any { isValid(it) }
    }

    private fun isValid(rawEquation: String): Boolean {
        val equation = rawEquation.split(" ")
        var i = 2
        var operandsResult =
            performOperation(equation[0], equation[1], equation[i++]) // At least, I have two numeric operands
        while (i < equation.size) {
            operandsResult = performOperation(operandsResult, equation[i], equation[i + 1])
            i += 2
        }
        return operandsResult == result
    }

    private fun performOperation(firstOperand: String, operator: String, secondOperand: String): Long {
        return performOperation(firstOperand.toLong(), operator, secondOperand)
    }

    private fun performOperation(firstOperand: Long, operator: String, secondOperand: String): Long {
        val secondOperandDigit = secondOperand.toLong()
        return when (operator) {
            "+" -> firstOperand + secondOperandDigit
            "*" -> firstOperand * secondOperandDigit
            else -> throw IllegalArgumentException("Invalid operator: $operator")
        }
    }

    private fun generateCombinations(elements: List<String>, length: Int): List<List<String>> {
        if (length == 0) return listOf(emptyList())     // Base case
        val smallerCombinations = generateCombinations(elements, length - 1)    // Recursive step
        return smallerCombinations.flatMap { combo ->
            elements.map { combo + it }
        }
    }

    // Function to build an expression from numbers and operators
    private fun buildExpression(numbers: List<Long>, operators: List<String>): String {
        return numbers.zip(operators + "").joinToString(" ") { (num, op) ->
            if (op.isNotEmpty()) "$num $op" else "$num"
        }
    }
}
