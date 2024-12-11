package lol.schroeder.aoc24

class Day11(private val input: List<String> = readInputLines("day11")) : Day() {
    override fun part1(): Any {
        val counter = PebbleCounter()
        val stones = input.first().split(" ").toMutableList()
            .sumOf { counter.count(it, 25) }

        return stones
    }

    override fun part2(): Any {
        val counter = PebbleCounter()
        val stones = input.first().split(" ").toMutableList()
            .sumOf { counter.count(it, 75) }

        return stones
    }

    class PebbleCounter {
        data class CacheKey(val num: String, val step: Int)

        private val cache = mutableMapOf<CacheKey, Long>()

        fun count(num: String, numSteps: Int): Long {
            if (numSteps == 0) return 1

            return cache.getOrPut(CacheKey(num, numSteps)) {
                when {
                    num == "0" -> count("1", numSteps - 1)
                    num.length.isEven() -> {
                        val (first, secondWithLeadingZeroes) = num.splitAtIndex(num.length / 2)
                        val second = secondWithLeadingZeroes.dropWhile { it == '0' }.ifEmpty { "0" }
                        count(first, numSteps - 1) + count(second, numSteps - 1)
                    }
                    else -> count((num.toLong() * 2024).toString(), numSteps - 1)
                }
            }
        }
    }
}

fun main() = Day11().solve()