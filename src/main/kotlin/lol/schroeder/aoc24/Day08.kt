package lol.schroeder.aoc24

class Day08(private val input: List<String> = readInputLines("day08")) : Day() {
    data class Coordinate(val x: Int, val y: Int)

    override fun part1(): Any {
        return input.flatMapIndexed { y, line -> line.mapIndexed { x, char -> char to Coordinate(x, y) } }
            .filter { it.first != '.' }
            .groupBy({ it.first }, { it.second })
            .mapValues { findAllAntinodes(it.value) }
            .flatMap { it.value }
            .flatMap { it.drop(1).take(1) }
            .toSet()
            .filter { it.x in input.first().indices && it.y in input.indices }
            .size
    }

    override fun part2(): Any {
        return input.flatMapIndexed { y, line -> line.mapIndexed { x, char -> char to Coordinate(x, y) } }
            .filter { it.first != '.' }
            .groupBy({ it.first }, { it.second })
            .mapValues { findAllAntinodes(it.value) }
            .flatMap { it.value }
            .flatMap { seq -> seq.takeWhile { it.x in input.first().indices && it.y in input.indices } }
            .toSet()
            .size
    }

    private fun findAllAntinodes(antennas: List<Coordinate>): List<Sequence<Coordinate>> {
        return antennas.elementPairs().map { (a1, a2) ->
            val slope = (a2.x - a1.x) to (a2.y - a1.y)
            listOf(
                generateSequence(a2) { Coordinate(it.x + slope.first, it.y + slope.second) },
                generateSequence(a1) { Coordinate(it.x - slope.first, it.y - slope.second) }
            )
        }.flatten().toList()
    }
}

fun main() = Day08().solve()