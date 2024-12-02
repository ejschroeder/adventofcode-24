package lol.schroeder.aoc24

import kotlin.math.abs

class Day01(private val input: List<String> = readInputLines("day01")) : Day() {
    private val combinedLists = input.map { it.extractInts().toPair() }
    private val listOne = combinedLists.map { it.first }
    private val listTwo = combinedLists.map { it.second }

    override fun part1(): Any {
        return listOne
            .sorted()
            .zip(listTwo.sorted()) { a, b -> abs(a - b) }
            .sum()
    }

    override fun part2(): Any {
        val frequencies = listTwo.groupingBy { it }.eachCount()
        return listOne.sumOf { it * (frequencies[it] ?: 0) }
    }
}

fun main() = Day01().solve()