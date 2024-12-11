package lol.schroeder.aoc24

import lol.schroeder.aoc24.util.Coordinate
import lol.schroeder.aoc24.util.Grid
import lol.schroeder.aoc24.util.toGrid

class Day10(private val input: List<String> = readInputLines("day10")) : Day() {
    override fun part1(): Any {
        val grid = input.flatMap { line -> line.map { it.digitToInt() } }
            .toGrid(input.first().length)

        val trailheads = grid.coordinates.filter { grid[it] == 0 }

        return trailheads.sumOf { getTrailheadScore(grid, it) }
    }

    override fun part2(): Any {
        val grid = input.flatMap { line -> line.map { it.digitToInt() } }
            .toGrid(input.first().length)

        val trailheads = grid.coordinates.filter { grid[it] == 0 }

        return trailheads.sumOf { getDistinctTrailheadScore(grid, it) }
    }

    private fun getDistinctTrailheadScore(grid: Grid<Int>, trailhead: Coordinate) : Int {
        val height = grid[trailhead]

        if (height == 9) return 1

        return grid.getNeighborCoordinates(trailhead)
            .filter { grid[it] == height + 1 }
            .sumOf { getDistinctTrailheadScore(grid, it) }
    }

    private fun getTrailheadScore(grid: Grid<Int>, trailhead: Coordinate, seenPeaks: MutableSet<Coordinate> = mutableSetOf()) : Int {
        val height = grid[trailhead]

        if (height == 9) {
            return if (seenPeaks.add(trailhead)) 1 else 0
        }

        return grid.getNeighborCoordinates(trailhead)
            .filter { grid[it] == height + 1 }
            .sumOf { getTrailheadScore(grid, it, seenPeaks) }
    }
}

fun main() = Day10().solve()