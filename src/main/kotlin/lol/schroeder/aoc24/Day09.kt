package lol.schroeder.aoc24

class Day09(private val input: List<String> = readInputLines("day09")) : Day() {
    override fun part1(): Any {
        val fileSystem = input.first().flatMapIndexed { idx, c ->
            if (idx.isEven()) List(c.digitToInt()) { idx / 2 } else List(c.digitToInt()) { -1 }
        }

        val mutableFileSystem = fileSystem.toMutableList()

        var currentIndex = 0
        var reversedPointer = fileSystem.size - 1

        while (currentIndex < reversedPointer) {
            if (mutableFileSystem[currentIndex] == -1) {
                mutableFileSystem[currentIndex] = fileSystem[reversedPointer--]

                while(fileSystem[reversedPointer] == -1) {
                    reversedPointer--
                }
            }
            currentIndex++
        }

        return mutableFileSystem.take(currentIndex + 1).mapIndexed{ idx, block -> block.toLong() * idx }.sum()
    }

    override fun part2(): Any {
        val mutableFileSystem = input.first().flatMapIndexed { idx, c ->
            if (idx.isEven()) List(c.digitToInt()) { idx / 2 } else List(c.digitToInt()) { -1 }
        }.toMutableList()


        var reversedPointer = mutableFileSystem.size - 1

        while (reversedPointer >= 0) {
            var blockSize = 0
            val blockId = mutableFileSystem[reversedPointer]
            while (reversedPointer >= 0 && mutableFileSystem[reversedPointer] == blockId) {
                blockSize++
                reversedPointer--
            }

            var currentIndex = 0
            var emptySpace = 0
            while (currentIndex <= reversedPointer && emptySpace < blockSize) {
                if (mutableFileSystem[currentIndex] == -1) {
                    emptySpace++
                } else {
                    emptySpace = 0
                }
                currentIndex++
            }

            if (emptySpace == blockSize) {
                repeat(blockSize) {
                    mutableFileSystem[currentIndex - it - 1] = mutableFileSystem[reversedPointer + it + 1]
                    mutableFileSystem[reversedPointer + it + 1] = -1
                }
            }

            while (reversedPointer >= 0 && mutableFileSystem[reversedPointer] == -1) {
                reversedPointer--
            }
        }

        return mutableFileSystem.mapIndexed{ idx, block -> if (block == -1) 0 else block.toLong() * idx }.sum()
    }
}

fun main() = Day09().solve()