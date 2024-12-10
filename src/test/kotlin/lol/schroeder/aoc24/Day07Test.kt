package lol.schroeder.aoc24

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day07Test {
    @Test
    fun part1() {
        val input = """
            
        """.trimIndent().lines()

        val result = Day06(input).part1()

        result shouldBe 41
    }

    @Test
    fun part2() {
        val input = """
            
        """.trimIndent().lines()

        val result = Day06(input).part2()

        result shouldBe 6
    }
}