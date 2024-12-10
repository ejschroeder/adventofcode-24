package lol.schroeder.aoc24

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day08Test {
    @Test
    fun part1() {
        val input = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
        """.trimIndent().lines()

        val result = Day08(input).part1()

        result shouldBe 14
    }

    @Test
    fun part2() {
        val input = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
        """.trimIndent().lines()

        val result = Day08(input).part2()

        result shouldBe 34
    }
}