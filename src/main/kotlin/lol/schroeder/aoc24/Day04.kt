package lol.schroeder.aoc24

class Day04(private val input: List<String> = readInputLines("day04")) : Day() {
    var dirs = listOf(
        -1 to -1,
        -1 to 0,
        -1 to 1,
        0 to -1,
        0 to 1,
        1 to -1,
        1 to 0,
        1 to 1
    )

    fun getNeighborsInDirection(grid: List<Char>, width: Int, height: Int, dir: Pair<Int, Int>, idx: Int, count: Int): List<Char> {
        val x = idx % width
        val y = idx / width

        return (0..count).map {
            (x + (it * dir.first)) to (y + (it * dir.second))
        }.filter { it.first in 0..<width && it.second in 0..<height }.map { grid[width * it.second + it.first] }
    }

    override fun part1(): Any {
        val width = input.first().length
        val height = input.size

        val grid = input.flatMap { it.toList() }

        return grid.withIndex()
            .filter { (_, c) -> c == 'X' }
            .flatMap { (idx, _) -> dirs.map { dir -> idx to getNeighborsInDirection(grid, width, height, dir, idx, 3) }.also { println(it) } }
            .count { it.second == "XMAS".toList() }
    }

    override fun part2(): Any {
        val grid = input.map { it.toList() }

        var count = 0
        for (x in grid.indices) {
            for (y in grid[0].indices) {
                if (grid[x][y] == 'A') {
                    val ulX = x + -1
                    val ulY = y + -1

                    val drX = x + 1
                    val drY = y + 1

                    val urX = x + -1
                    val urY = y + 1

                    val dlX = x + 1
                    val dlY = y + -1

                    if (listOf(ulX, drX, urX, dlX).all { it in grid.indices } && listOf(ulY, drY, urY, dlY).all { it in grid[0].indices }) {
                        val ul = grid[ulX][ulY]
                        val dr = grid[drX][drY]

                        val ur = grid[urX][urY]
                        val dl = grid[dlX][dlY]

                        if (listOf(ul, dr, ur, dl).all { it == 'M' || it == 'S' } && ul != dr && ur != dl) {
                            count++
                        }
                    }
                }
            }
        }
        return count
    }
}

fun main() = Day04().solve()