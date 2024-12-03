package lol.schroeder.aoc24

class Day03(private val input: String = readInputLines("day03").joinToString()) : Day() {
    private val reg = Regex("mul\\(\\d+,\\d+\\)")

    override fun part1(): Any {
        return input.executeMuls()
    }

    override fun part2(): Any {
        var memory = input
        var sum = 0

        while(memory.isNotEmpty()) {
            val workingString = memory.split("don't()", limit = 2)
            sum += workingString[0].executeMuls()
            memory = workingString.getOrNull(1)?.run { substringAfter("do()", missingDelimiterValue = "") } ?: ""
        }

        return sum
    }

    private fun String.executeMuls() = reg.findAll(this).sumOf { it.value.extractInts().product() }
}

fun main() = Day03().solve()