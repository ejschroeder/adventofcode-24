package lol.schroeder.aoc24

class Day02(private val input: List<String> = readInputLines("day02")) : Day() {
    override fun part1(): Any {
        return input.map { it.extractInts() }
            .count { isSafe(it) }
    }

    override fun part2(): Any {
        return input.map { it.extractInts() }
            .count { isSafeDampened(it) }
    }

    private fun isSafe(report: List<Int>): Boolean {
        val pairs = report.zipWithNext()
        return pairs.all { it.first - it.second in 1..3 } || pairs.all { it.first - it.second in -3..-1 }
    }

    private fun isSafeDampened(report: List<Int>): Boolean {
        if (isSafe(report)) return true

        return report.indices.any {
            val newReport = report.toMutableList()
            newReport.removeAt(it)
            isSafe(newReport)
        }
    }
}

fun main() = Day02().solve()