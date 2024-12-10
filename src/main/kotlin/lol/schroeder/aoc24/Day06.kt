package lol.schroeder.aoc24

class Day06(private val input: List<String> = readInputLines("day06")) : Day() {
    val dirs = listOf(
        0 to -1,
        1 to 0,
        0 to 1,
        -1 to 0
    )

    data class Location(val x: Int, val y: Int)

    override fun part1(): Any {
        val width = input.first().length
        val height = input.size
        val grid = input.flatMap { it.toList() }

        val idx = grid.indexOf('^')
        val start = Location(idx % width, idx / width)
        val seen = mutableSetOf<Location>()
        var dir = 0
        var current = start
        while (isInBounds(current, width, height)) {
            val index = current.y * width + current.x
            if (grid[index] != '#') {
                seen.add(current)
            } else {
                val currentDir = dirs[dir]

                current = Location(current.x - currentDir.first, current.y - currentDir.second)
                dir = (dir + 1) % dirs.size
            }

            val (xOffset, yOffset) = dirs[dir]


            val newX = current.x + xOffset
            val newY = current.y + yOffset
            current = Location(newX, newY)
        }
        return seen.size
    }

    private fun isLoop(grid: List<Char>, width: Int, height: Int, obstacle: Int): Boolean {
        val idx = grid.indexOf('^')
        val start = LocationDirection(idx % width, idx / width, 0)
        val seen = mutableSetOf<LocationDirection>()
        var current = start
        while (isInBounds(current, width, height) && current !in seen) {
            val index = current.y * width + current.x
            var dir = current.dir
            if (grid[index] != '#' && index != obstacle) {
                seen.add(current)
            } else {
                val currentDir = dirs[dir]

                current = LocationDirection(current.x - currentDir.first, current.y - currentDir.second, dir)
                dir = (dir + 1) % dirs.size
            }

            val (xOffset, yOffset) = dirs[dir]

            val newX = current.x + xOffset
            val newY = current.y + yOffset
            current = LocationDirection(newX, newY, dir)
        }

        return current in seen
    }

    private fun isInBounds(location: Location, width: Int, height: Int): Boolean {
        return location.x in 0 until width && location.y in 0 until height
    }
    private fun isInBounds(location: LocationDirection, width: Int, height: Int): Boolean {
        return isInBounds(Location(location.x, location.y), width, height)
    }

    data class LocationDirection(val x: Int, val y: Int, val dir: Int)

    override fun part2(): Any {
        val width = input.first().length
        val height = input.size
        val grid = input.flatMap { it.toList() }

        return grid.withIndex()
            .filter { it.value == '.' }
            .count { isLoop(grid, width, height, it.index) }
    }
}

fun main() = Day06().solve()