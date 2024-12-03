package lol.schroeder.aoc24

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun part1() {
        val input = """
            xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
        """.trimIndent()

        val result = Day03(input).part1()

        result shouldBe 161
    }

    @Test
    fun part2() {
        val input = """
            xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
        """.trimIndent()

        val result = Day03(input).part2()

        result shouldBe 48
    }
}