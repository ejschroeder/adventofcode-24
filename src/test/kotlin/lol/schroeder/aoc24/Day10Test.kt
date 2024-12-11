package lol.schroeder.aoc24

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun part1() {
        val input = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
        """.trimIndent().lines()

        val result = Day10(input).part1()

        result shouldBe 36
    }

    @Test
    fun part2() {
        val input = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
        """.trimIndent().lines()

        val result = Day10(input).part2()

        result shouldBe 81
    }
}