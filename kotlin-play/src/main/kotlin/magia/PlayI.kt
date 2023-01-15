package magia

import kotlin.math.*

fun main() {

    // companion object, constructor
    println("c1=${C1.counter}")
    C1.plus(2)
    println("c1=${C1.counter}")
    val c1 = C1(10, "email")
    println("c1: $c1")

    // vararg, default var, forloop
    fun printAll(vararg msgs: String, prefix: String = "yo") {
        for ((idx, msg) in msgs.withIndex()) println("$prefix: idx=$idx, msg=$msg")
    }
    printAll("A", "B", "C", "D")

    // infix
    infix fun Int.times(s: String) = s.repeat(this)
    println(5 times "hi")
    println("a" to 1) // create a tuple

    // ternary, elvis operator
    println(if (true) "true" else "false")
    println(null ?: "not null")

    // generic function, expansion
    fun <T> getListOf(vararg args: T) = listOf(*args)
    println(getListOf<Number>(1, 2, 4_000))

    // class inheritance
    val catMaid = CatMaid("honoka")
    catMaid.say()

    // interface conflict
    val c12 = C12()
    c12.say()

    // when, pattern matching, map, lambda
    println(whenAssign(99L))
    println(listOf(-1, 5, 100).map { whenAssign2(it) })

    // IntRange type
    println((0..10 step 3).toList())
    println((10 downTo 0 step 3).reversed().toSet())

    // Map
    val mutableMap = mutableMapOf(1 to 10, 2 to 20, 3 to 30)
    mutableMap.put(4, 40)
    println(mutableMap.getOrDefault(4, 0))
    println(mutableMap.getOrElse(5) { 41 })
    mutableMap.forEach { k, v -> println("k=$k, v=$v") }

    println("--------------")

    // List, Set, filter, map
    val mset = listOf(1, 1, 1, 2, 3, 4, 5, 6).toMutableSet()
    val mset2 = mset.map { it - 1 }
    println(mset2.filter { it <= 3 })
    // any, all, none
    println(mset.all { it > 0 })
    // find, findLast, count
    println(mset.find { it % 2 == 0 })
    println(mset.count { it == 1 })
    // partition: make 2 sub lists. True sublist comes first.
    val (odds, evens) = mset.partition { it % 2 == 1 }
    println(odds)
    println(evens)
    // sorted, sortedBy, sortedDescending, sortedByDescending
    println(odds.sorted())
    println(odds.sortedByDescending { abs(it) })

    // zip (similar with Flux/Stream)
    // if odds=1,3,5 and evens=2,4, then zippedList=[(1, 2), (3, 4)]
    val zippedList = odds.zip(evens)
    println(zippedList)

    // flatMap, merge two lists, pair
    val mergedList1 = odds + evens // OR odds.toMutableSet().addAll(evens)
    val mergedList2 = listOf(odds, evens).flatten()
    val mergedList3 = listOf(odds, evens).flatMap { it }
    println(mergedList1 == mergedList2 && mergedList2 == mergedList3)
    val changedList = listOf(odds, evens).flatMap { list -> list.map { n -> n + 10 } }
    println(changedList)
    var unzippedList = zippedList.flatMap { pair -> listOf(pair.first, pair.second) }
    println(unzippedList)

    // Destructuring. underscore to skip.
    val (x, _, z) = arrayOf(1, 2, 3)
    println("x=$x, z=$z")
}

class C1(val id: Number, var email: String) {
    companion object Obj {
        var counter = 0
        @JvmStatic
        fun plus(num: Int) {
            counter += num
        }
    }
    override fun toString() = "id=$id||email=$email"
}

open class Cat(val name: String, val age: Int) {
    open fun say() {
        println("mew~$name~~$age")
    }
}

class CatMaid(name: String) : Cat(name = name, age = 17) {
    override fun say() {
        println("mew sama~$name~~$age")
    }
}

interface I1 {
    fun say() {
        println("I1~")
    }
}

interface I2 {
    fun say() {
        println("I2~")
    }
}

class C12 : I1, I2 {
    override fun say() {
        super<I1>.say()
        super<I2>.say()
    }
}

fun whenAssign(obj: Any) =
        when (obj) {
            1 -> 1
            is Int -> 2
            is Long -> 4
            else -> 999
        }

fun whenAssign2(n: Int) =
        when {
            n < 0 -> 1
            n >= 0 && n < 10 -> 2
            else -> 999
        }
