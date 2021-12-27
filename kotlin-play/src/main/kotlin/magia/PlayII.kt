package magia

fun main() {

    // data class
    val user1 = User("u1", 17)
    val user2 = user1.copy("u2")
    println("$user1||$user2")

    // enum with properties
    println(Color.RED.name)
    val red2 = Color2.RED
    println("${red2.rgb}||${red2.getOutput()}")

    // sealed class: An abstract class used as enum, no need "else" in "when"
    fun watchAnime(p: Precure) =
            when (p) {
                is FreshPrecure -> "Fresh"
                is SuitePrecure -> "Suite"
            }
    println(watchAnime(FreshPrecure("Pine", 13)))

    // object declaration outside: singleton, cannot assign
    PrecureManager.playAllStar()
    // object assignment inside -> anonymous instance like Javascript
    val obj =
            object {
                val name = "no name"
            }
    println(obj.name)

    // high-order function, extension functions/properties
    // println(listOf(2, 3, 6, 9).minByOrNull { it.price % 5 }) ?: 0
    fun List<Int>.myMinBy(f: (Int) -> Int): Int {
        var min: Int = this[0]
        for (n in this) min = if (f(n) > f(min)) min else n
        return min
    }
    println(listOf(2, 3, 6, 9).myMinBy { it % 5 })
    println(listOf(2, 3, 6, 9).myMinBy { it + 1 % 3 })
    // just like Javascript
    fun Any?.sayHi(): Unit {
        println("sayHi")
    }
    null.sayHi()

    // associateBy: map result will lost duplicates
    // groupBy: map result will put duplicates in list
    val userList = listOf(User("u1", 15), User("u2", 16), User("u3", 15))
    println(userList.associateBy { it.age })
    println(userList.associateBy(User::age, User::name))
    println(userList.groupBy { it.age })
    println(userList.groupBy(User::age, User::name))

    // let, apply; run, with, also;
    // https://play.kotlinlang.org/byExample/06_scope_functions/01_let
    fun printIfNotNull(str: String?): Int =
            str?.let {
                println("In Let, not null: $it")
                it.length
            }
                    ?: 999
    printIfNotNull("not null")
    val user1x =
            user1
                    .apply {
                        name = "u1b"
                        println("newname=$name, age=$age")
                    }
                    .apply {
                        name = "u1c"
                        println("newname=$name, age=$age")
                    }
    println(user1x) // name=u1c

    // delegation: when you don't want to manually implement all methods of HashSet
    // lazy function: delegation
    // https://blog.csdn.net/baidu_39589150/article/details/111908226

    // More TODO: typealias, Coroutines, this@ expression, Annotations(like SpringBoot)
    // https://kotlinlang.org/docs/coroutines-overview.html

}

data class User(var name: String, val age: Int)

enum class Color {
    RED,
    BLUE,
    GREEN
}

enum class Color2(val rgb: Int) {
    RED(1),
    BLUE(2),
    GREEN(3);
    fun getOutput() = this.rgb
}

sealed class Precure(val name: String)

class FreshPrecure(val pname: String, val age: Int) : Precure(pname)

class SuitePrecure(val pname: String, val age: Int) : Precure(pname)

object PrecureManager {
    fun playAllStar() {
        println("All Star")
    }
}
