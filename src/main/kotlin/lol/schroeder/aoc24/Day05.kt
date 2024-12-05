package lol.schroeder.aoc24

class Day05(private val input: List<String> = readInputLines("day05")) : Day() {
    override fun part1(): Any {
        val (rules, updates) = parseInput()

        val comparator = RuleComparator(rules)

        return updates
            .filter { comparator.isSorted(it) }
            .sumOf { it[it.size / 2] }
    }

    override fun part2(): Any {
        val (rules, updates) = parseInput()

        val comparator = RuleComparator(rules)

        return updates.filter { !comparator.isSorted(it) }
            .sumOf { it.sortedWith(comparator)[it.size / 2] }
    }

    class RuleComparator(private val rules: Map<Int, List<Int>>) : Comparator<Int> {
        override fun compare(o1: Int?, o2: Int?): Int {
            val numRules = rules[o1] ?: listOf()
            return if (o2 in numRules) -1 else 1
        }

        fun isSorted(list: List<Int>) = list.zipWithNext()
            .map { (o1, o2) -> compare(o1, o2) }
            .all { it == -1 }
    }

    private fun parseInput(): Pair<Map<Int, List<Int>>, List<List<Int>>> {
        val (rules, updates) = input.splitOn { it.isBlank() }.toList()

        val ruleMap = rules.map {
            val (one, two) = it.extractInts()
            one to two
        }.groupBy({ it.first }, { it.second })

        return ruleMap to updates.map { it.extractInts() }
    }
}

fun main() = Day05().solve()