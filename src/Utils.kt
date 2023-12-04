import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Reads lines from the given input txt file for day1.
 */
fun readInputDay1(name: String) = Path("src/day1/$name.txt").readLines()

/**
 * Reads lines from the given input txt file for day2.
 */
fun readInputDay2(name: String) = Path("src/day2/$name.txt").readLines()

fun readInputDay3(name: String) = Path("src/day3/$name.txt").readLines()
fun readInputDay4(name: String) = Path("src/day4/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Extension function to convert string digit to number
 */

