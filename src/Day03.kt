import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input.matchesPart1()
            .foldMatches()
    }

    fun part2(input: List<String>): Int {
        return input.matchesPart2()
            .foldMatches()
    }

    // Or read a large test input from the `src/Day03_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 48)

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

private val pattern = "do\\(\\)|don't\\(\\)|mul\\(([0-9]*),([0-9]*)\\)".toRegex()

private fun List<String>.matchesPart1(): List<MatchResult> = flatMap { pattern.findAll(it) }
    .filter {
        !it.value.startsWith("do")
    }

private fun List<String>.matchesPart2(): List<MatchResult> = flatMap { pattern.findAll(it) }

private fun List<MatchResult>.foldMatches(): Int = fold(Accumulator()) { acc, result ->
    when {
        result.value.startsWith("mul") && acc.parse -> acc.copy(
            sum = acc.sum + result.multiply()
        )

        result.value.startsWith("don't") -> acc.copy(parse = false)
        result.value.startsWith("do") -> acc.copy(parse = true)

        else -> acc
    }
}.sum

private fun MatchResult.multiply(): Int {
    val first = groups[1]?.value?.toInt() ?: 0
    val second = groups[2]?.value?.toInt() ?: 0

    return first * second
}

private data class Accumulator(val sum: Int = 0, val parse: Boolean = true)

