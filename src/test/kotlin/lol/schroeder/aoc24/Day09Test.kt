package lol.schroeder.aoc24

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day09Test {
    @Test
    fun part1() {
        val input = """
            2333133121414131402
        """.trimIndent().lines()

        val result = Day09(input).part1()

        result shouldBe 1928
    }

    @Test
    fun part2() {
        val input = """
            2333133121414131402
        """.trimIndent().lines()

        val result = Day09(input).part2()

        result shouldBe 2858
    }

    @Test
    fun part2EdgeCase() {
        val input = """
            1010101010101010101010
        """.trimIndent().lines()

        val result = Day09(input).part2()

        result shouldBe 385
    }
}