package lol.schroeder.aoc24

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun part1() {
        val input = """
            125 17
        """.trimIndent().lines()

        val result = Day11(input).part1()

        result shouldBe 55312
    }

    @Test
    fun part2() {
        val input = """
            125 17
        """.trimIndent().lines()

        val result = Day11(input).part2()

        result shouldBe 65601038650482L
    }
}