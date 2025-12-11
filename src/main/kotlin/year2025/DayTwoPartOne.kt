package com.github.juanmougan.advent.year2025


class DayTwo {

    fun isInvalid(n: String): Boolean {
        // Make sure that the string is split evenly - can't have a reminder char!
        val (first, second) = n.splitEvenly() ?: return false
        return first == second
    }

    fun String.splitEvenly(): Pair<String, String>? {
        if (length % 2 != 0) return null

        val chunks = chunked(length / 2)
        return chunks[0] to chunks[1]
    }

    fun parseInput(input: String): List<String> {
        return input.split(",")
    }
}

fun main() {
    val day = DayTwo()
    val numbers = day.parseInput("4077-5314,527473787-527596071,709-872,2487-3128,6522872-6618473,69137-81535,7276-8396,93812865-93928569,283900-352379,72-83,7373727756-7373754121,41389868-41438993,5757-6921,85-102,2-16,205918-243465,842786811-842935210,578553879-578609405,9881643-10095708,771165-985774,592441-692926,7427694-7538897,977-1245,44435414-44469747,74184149-74342346,433590-529427,19061209-19292668,531980-562808,34094-40289,4148369957-4148478173,67705780-67877150,20-42,8501-10229,1423280262-1423531012,1926-2452,85940-109708,293-351,53-71")
    val sum = numbers.flatMap { it.parseIfRange() }.filter { day.isInvalid(it) }.sumOf { number -> number.toLong() }
    println(sum)
}

private fun String.parseIfRange(): List<String> {
    if (this.contains("-")) {
        val (start, end) = this.split("-")
        return (start.toLong()..end.toLong()).map { it.toString() }
    } else return listOf(this)
}
