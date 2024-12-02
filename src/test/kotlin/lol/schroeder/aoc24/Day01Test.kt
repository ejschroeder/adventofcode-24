package lol.schroeder.aoc24

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun part1() {
        val input = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent().lines()

        val result = Day01(input).part1()

        result shouldBe 11
    }

    @Test
    fun part2() {
        val input = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent().lines()

        val result = Day01(input).part2()

        result shouldBe 31
    }
}