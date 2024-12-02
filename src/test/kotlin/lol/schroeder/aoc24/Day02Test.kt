package lol.schroeder.aoc24;

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun part1() {
        val input = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent().lines()

        val result = Day02(input).part1()

        result shouldBe 2
    }

    @Test
    fun part2() {
        val input = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent().lines()

        val result = Day02(input).part2()

        result shouldBe 4
    }
}
