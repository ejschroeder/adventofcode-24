package lol.schroeder.aoc24

val days = listOf(
    Day01(),
    Day02(),
)

fun main() {
    days.forEach { it.solve() }
}