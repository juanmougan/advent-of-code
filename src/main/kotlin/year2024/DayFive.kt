package com.github.juanmougan.advent.year2024

import com.github.juanmougan.advent.common.RulesEngine
import com.github.juanmougan.advent.common.readLinesWithDelimiter


class DayFive(private val rulesEngine: RulesEngine) {
    fun dayFivePartOneSolution(updatesFileName: String): Int {
        val updates = readLinesWithDelimiter(
            fileName = updatesFileName,
            delimiter = ","
        ).map { line -> line.map { number -> number.toInt() } }
        val filteredUpdates = filterValidUpdates(updates)
        return filteredUpdates.sumMiddleElements()
    }

    private fun filterValidUpdates(updates: List<List<Int>>): List<List<Int>> {
        val filteredUpdates: MutableList<List<Int>> = mutableListOf()
        loop@ for (update in updates) {
            for (i in 0 until update.size - 1) {
                for (j in (i + 1) until update.size) {
                    val first = update[i]
                    val second = update[j]
                    if (!rulesEngine.firstElementIsBeforeSecond(first, second)) {
                        continue@loop   // Skip this update
                    }
                }
            }
            // The update is valid
            filteredUpdates.add(update.toList())
        }
        return filteredUpdates.toList()
    }

    private fun List<List<Int>>.sumMiddleElements(): Int {
        return map { list -> list.middleElement() }.reduce(Int::plus)
    }

    private fun <T> List<T>.middleElement(): T {
        if (this.size.mod(2) == 0) throw IllegalArgumentException("List must be odd in order to have a middle element")
        return this[this.size / 2]
    }
}
