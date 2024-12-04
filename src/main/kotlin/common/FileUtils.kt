package com.github.juanmougan.advent.common

import java.io.File

fun getFile(fileName: String) = File(ClassLoader.getSystemResource(fileName).file)

fun readLines(fileName: String) = getFile(fileName).readLines()

fun readLinesHavingSingleValue(fileName: String) = readLines(fileName)

fun readLinesHavingTwoValues(fileName: String, delimiter: String) = readLines(fileName)
    .map { it.split(delimiter) }
    .map { line -> line.let { col -> col[0] to col[1] } }

fun readLinesWithDelimiter(fileName: String, delimiter: String = " ") = readLines(fileName)
    .map { it.split(delimiter) }
