package com.github.juanmougan.advent.common

import java.io.File

private fun getFile(fileName: String) = File(ClassLoader.getSystemResource(fileName).file)

fun readLinesHavingSingleValue(fileName: String) = getFile(fileName).readLines()

fun readLinesHavingTwoValues(fileName: String, delimiter: String) = getFile(fileName).readLines()
    .map { it.split(delimiter) }
    .map { line -> line.let { col -> col[0] to col[1] } }
