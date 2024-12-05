package lol.schroeder.aoc24

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.max
import kotlin.math.min

fun readInputLines(path: String) = getInputResourceAsFile(path).readLines()

fun readInputText(path: String) = getInputResourceAsFile(path).readText()

fun getInputResourceAsFile(path: String): File {
    val uri = object {}.javaClass.getResource("/$path.txt")?.toURI()
        ?: throw ResourceNotFoundException(path)

    return File(uri)
}

class ResourceNotFoundException(path: String) : RuntimeException("Resource '$path' not found.")

fun Int.isEven() = this % 2 == 0
fun Int.isOdd() = this % 2 == 1

tailrec fun gcd(a: Int, b: Int): Int {
    if (b == 0) return a
    return gcd(b, a.mod(b))
}

fun gcd(a: Int, b: Int, vararg others: Int): Int {
    return others.fold(gcd(a, b)) { acc, i -> gcd(acc, i) }
}

fun lcm(a: Int, b: Int): Int {
    return a / gcd(a, b) * b
}

fun lcm(a: Long, b: Long): Long {
    val larger = max(a, b)
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

operator fun IntRange.contains(other: IntRange) = first <= other.first && last >= other.last
operator fun LongRange.contains(other: LongRange) = first <= other.first && last >= other.last
infix fun IntRange.overlaps(other: IntRange) = first <= other.last && last >= other.first
infix fun LongRange.overlaps(other: LongRange) = first <= other.last && last >= other.first
fun IntRange.merge(other: IntRange): IntRange? {
    val min = min(this.first, other.first)
    val max = max(this.last, other.last)
    return (min..max).takeIf { overlaps(other) }
}

fun LongRange.merge(other: LongRange): LongRange {
    if (!overlaps(other)) throw IllegalArgumentException("Range does not overlap with other")
    val min = min(this.first, other.first)
    val max = max(this.last, other.last)
    return (min..max)
}

fun String.isUpperCase(): Boolean = this.all { it.isUpperCase() }
fun String.splitAtIndex(index: Int) = substring(0, index) to substring(index)
fun String.splitOnFirst(str: String) = split(str, limit = 2).let {
    Pair(it[0], it.getOrElse(1) { "" })
}
fun String.isNumeric(): Boolean = this.all { it.isDigit() }
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
fun String.extractInts() = Regex("([+\\-])?\\d+")
    .findAll(this)
    .mapNotNull { it.value.toIntOrNull() }
    .toList()

fun <T> List<T>.toPair(): Pair<T, T> {
    require(size == 2) { "list must contain exactly 2 elements" }
    return first() to last()
}

fun String.extractAll(pattern: String) = Regex(pattern)
    .findAll(this)
    .map { it.value }
    .toList()

fun String.hexToBinaryString() = map { it.hexToNibble() }.joinToString(separator = "")
fun Char.hexToNibble() = digitToInt(radix = 16)
    .toString(radix = 2)
    .padStart(length = 4, padChar = '0')

fun <T> String.mapGroups(pattern: String, transform: (MatchResult.Destructured) -> T) =
    mapGroups(Regex(pattern), transform)

fun <T> String.mapGroups(regex: Regex, transform: (MatchResult.Destructured) -> T): T {
    val destructuredResult = regex.matchEntire(this)?.destructured
        ?: throw IllegalStateException("No match found for given regex '${regex}'")
    return transform(destructuredResult)
}

fun <T> Iterable<T>.rest() = drop(1)

fun Iterable<Int>.asLongs() = map(Int::toLong)
fun Iterable<Long>.product() = reduce(Long::times)
fun Iterable<Int>.product() = reduce(Int::times)
fun Sequence<Int>.product() = reduce(Int::times)
fun Sequence<Long>.product() = reduce(Long::times)

fun <T, R : Comparable<R>> Iterable<T>.minMaxOf(selector: (T) -> R): Pair<R, R> {
    val iter = iterator()
    if (!iter.hasNext()) throw NoSuchElementException()

    val next = iter.next()
    var min = selector(next)
    var max = selector(next)

    iter.forEachRemaining {
        val item = selector(it)
        if (item < min) min = item
        if (item > max) max = item
    }

    return min to max
}

fun <T> Iterable<T>.takeWhileInclusive(predicate: (T) -> Boolean) = sequence {
    with(iterator()) {
        while (hasNext()) {
            val next = next()
            yield(next)
            if (!predicate(next)) break
        }
    }
}

fun <K, V, T> Map<K, V>.merge(other: Map<K, V>, valueMapper: (List<V>) -> T): Map<K, T> {
    return (this.asSequence() + other.asSequence())
        .groupBy({ it.key }, { it.value })
        .mapValues { (_, values) -> valueMapper(values) }
}

fun <T> Iterable<T>.elementPairs(): Sequence<Pair<T, T>> = sequence {
    val list = toList()
    for(i in 0 ..< list.size-1)
        for(j in i+1 ..< list.size)
            yield(list[i] to list[j])
}

fun <T> Collection<T>.findOrThrow(predicate: (T) -> Boolean): T = find(predicate) ?: throw IllegalStateException("Item was not found in the list")

fun <T> List<T>.splitOn(predicate: (T) -> Boolean): Sequence<List<T>> {
    return sequence {
        var group = mutableListOf<T>()
        for(element in this@splitOn) {
            if (predicate(element)) {
                yield(group)
                group = mutableListOf()
            } else {
                group += element
            }
        }
        yield(group)
    }
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    return (this[0].indices).map { i -> (this.indices).map { j -> this[j][i] } }
}

fun <T> Iterable<T>.cycle() = sequence {
    var iterator = iterator()

    if (!iterator.hasNext()) return@sequence

    while (true) {
        yield(iterator.next())

        if (!iterator.hasNext())
            iterator = iterator()
    }
}