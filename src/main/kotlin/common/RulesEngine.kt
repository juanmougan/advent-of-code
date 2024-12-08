package com.github.juanmougan.advent.common

import java.util.Stack

class RulesEngine(val rulesFileName: String, var rules: List<Pair<Int, Int>> = mutableListOf()) {
    private val graph = mutableMapOf<Int, MutableList<Int>>()
    private val reverseGraph = mutableMapOf<Int, MutableList<Int>>()

    init {
        initRules()
        buildGraph()
    }

    private fun initRules() {
        rules = rulesFileName.toPairs("|").map {
            Pair(first = it.first.toInt(), second = it.second.toInt())
        }
    }

    // Step 1: Build adjacency list for direct and reverse relationships
    private fun buildGraph() {
        rules.forEach { (from, to) ->
            graph.computeIfAbsent(from) { mutableListOf() }.add(to)
            reverseGraph.computeIfAbsent(to) { mutableListOf() }.add(from)
            graph.putIfAbsent(to, mutableListOf()) // Ensure every node is included
            reverseGraph.putIfAbsent(from, mutableListOf())
        }
    }

    // Step 2: Check if 'first' comes before 'second' transitively
    fun firstElementIsBeforeSecond(first: Int, second: Int): Boolean {
        return dfs(first, second, graph)
    }

    // Helper: Depth-First Search
    private fun dfs(start: Int, target: Int, graph: Map<Int, List<Int>>): Boolean {
        val visited = mutableSetOf<Int>()
        val stack = Stack<Int>()
        stack.push(start)

        while (stack.isNotEmpty()) {
            val current = stack.pop()
            if (current == target) return true
            if (visited.add(current)) {
                graph[current]?.forEach { neighbor ->
                    stack.push(neighbor)
                }
            }
        }
        return false
    }
}
