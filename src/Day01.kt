import kotlin.collections.unzip
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map(String::splitInPair)
            .unzip()
            .sorted()
            .distances()
            .sum()
    }

    fun part2(input: List<String>): Int {
        return input
            .map(String::splitInPair)
            .unzip()
            .sorted()
            .similarities()
            .sum()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun String.splitInPair() : Pair<Int, Int> = split("\\s+".toRegex())
    .map(String::toInt)
    .let { it.first() to it.last() }

private fun Pair<List<Int>, List<Int>>.sorted(): Pair<List<Int>, List<Int>> {
    return copy(
        first.sorted(),
        second.sorted()
    )
}

private fun Pair<List<Int>, List<Int>>.distances(): List<Int> {
    return first.mapIndexed { index, item ->
        abs(item - second[index])
    }
}

private fun Pair<List<Int>, List<Int>>.similarities(): List<Int> {
    return first.map { item ->
        item * second.filter { it == item }.size
    }
}
