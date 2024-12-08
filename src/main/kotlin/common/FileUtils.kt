package com.github.juanmougan.advent.common

import com.github.juanmougan.advent.year2024.Equation
import java.io.File

fun getFile(fileName: String) = File(ClassLoader.getSystemResource(fileName).file)

fun readLines(fileName: String) = getFile(fileName).readLines()

fun readLinesHavingSingleValue(fileName: String) = readLines(fileName)

fun readLinesHavingTwoValues(fileName: String, delimiter: String) = readLines(fileName)
    .map { it.split(delimiter) }
    .map { line -> line.let { col -> col[0] to col[1] } }

fun readLinesWithDelimiter(fileName: String, delimiter: String = " ") = readLines(fileName)
    .map { it.split(delimiter) }

fun String.toPairs(delimiter: String): List<Pair<String, String>> = getFile(this).readLines()
    .map { line -> Pair(first = line.split(delimiter)[0], second = line.split(delimiter)[1]) }

fun String.toEquation(): Equation {
    val parts = split(":")
    return Equation(result = parts.first().toLong(), parts[1].trim().split(" ").map { it.toLong() })
}
