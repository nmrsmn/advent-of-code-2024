import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map(String::splitToList)
            .filter(List<Int>::validReport)
            .size
    }

    fun part2(input: List<String>): Int {
        return input
            .map(String::splitToList)
            .filter(List<Int>::validReportProblemDampener)
            .size
    }

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

private fun String.splitToList() : List<Int> = split("\\s+".toRegex())
    .map(String::toInt)

private fun List<Int>.validReport(): Boolean {
    return zipWithNext()
        .map { it.first - it.second }
        .let { it.validDifference() && it.allIncreasingOrDecreasing() }
}

private fun List<Int>.validReportProblemDampener(): Boolean {
    val a = List(size) { index ->
        filterIndexed { i, _ -> i != index }
            .validReport()
    }

    return a.any { it }
}

private fun List<Int>.validDifference() = all {
    (1 .. 3).contains(abs(it))
}

private fun List<Int>.allIncreasingOrDecreasing() = all { it > 0 } || all { it < 0 }
