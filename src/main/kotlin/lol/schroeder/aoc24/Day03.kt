package lol.schroeder.aoc24

class Day03(private val input: String = readInputLines("day03").joinToString()) : Day() {
    private val reg = "mul\\(\\d+,\\d+\\)".toRegex()
    private val dontDoReg = "don't\\(\\).*?do\\(\\)|don't\\(\\).*?$".toRegex()

    override fun part1() = input.executeMuls()

    override fun part2() = input.replace(dontDoReg, "").executeMuls()

    private fun String.executeMuls() = reg.findAll(this).sumOf { it.value.extractInts().product() }
}

fun main() = Day03().solve()