package lol.schroeder.aoc24

class Day07(private val input: List<String> = readInputLines("day07")) : Day() {
    data class Equation(val testValue: Long, val numbers: List<Long>) {

        fun isValid(): Boolean {
            return isValidInternal(numbers.first(), numbers.drop(1))
        }

        fun isValidWithOr(): Boolean {
            return isValidInternalWithOr(numbers.first(), numbers.drop(1))
        }

        private fun isValidInternal(current: Long, nums: List<Long>): Boolean {
            if (nums.isEmpty()) return current == testValue

            return isValidInternal(current + nums.first(), nums.drop(1)) || isValidInternal(current * nums.first(), nums.drop(1))
        }

        private fun isValidInternalWithOr(current: Long, nums: List<Long>): Boolean {
            if (nums.isEmpty()) return current == testValue

            val concat = "$current${nums.first()}".toLong()

            return isValidInternalWithOr(current + nums.first(), nums.drop(1))
                    || isValidInternalWithOr(current * nums.first(), nums.drop(1))
                    || isValidInternalWithOr(concat, nums.drop(1))
        }

    }

    override fun part1(): Any {
        val equations = input.map { it.split(": ") }
            .map { Equation(it[0].toLong(), it[1].extractInts().asLongs()) }

        return equations.filter { it.isValid() }.sumOf { it.testValue}
    }

    override fun part2(): Any {
        val equations = input.map { it.split(": ") }
            .map { Equation(it[0].toLong(), it[1].extractInts().asLongs()) }

        return equations.filter { it.isValidWithOr() }.sumOf { it.testValue}
    }
}

fun main() = Day07().solve()