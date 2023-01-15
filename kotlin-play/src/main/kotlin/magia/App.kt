/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package magiaOld

fun main() {

    val app = App()
    println(app.greeting)

    FreshPrecure().dance()

    var pred: ((String) -> Boolean) = { it -> it.length > 2 }

    val list = listOf("Java", "Javascript", "Kotlin", "Go")
    list.filterNotNull().take(3).forEach { println(it) }

    var s: String? = null
    app.greeting = s ?: "www"

    println(app.greeting)

    list.forEachIndexed { index, s2 -> println("www $index - $s2") }

    test(*list.toTypedArray())
}

fun test(vararg tests: String) {
    tests.forEach { println("ttt: $it") }
}

class Alien(val name: String) {
    fun think() {
        println("Thinking: $name")
    }
}

class App {
    var greeting: String = ""
        get() {
            return field
        }
        set(value) {
            field = value + "aaa"
        }
}

interface PrecureProvider {
    fun dance()
    val name: String
}

open class FreshPrecure : PrecureProvider {
    override val name: String = ""
        get() {
            return "${field}w"
        }

    override fun dance() = println("FreshPrecureOP")
}

class Empty

data class DC(val price: Int)
