package com.example.myapplication.kotlinFunction

fun main() {
    //tryHigherOrderFunction()
    tryLambdaFunction()
}

fun tryLambdaFunction() {
    val sumLambda: (Int, Int, Int) -> Int = { i1: Int, i2: Int, i3: Int -> i1 + i2 + i3 }
    println(sumLambda(1, 2, 3))

    val arr: ArrayList<Int> = arrayListOf(1, 3, 2, 4, 3, 4, 1)
    val maxIndex = arr.indexOf(arr.maxOrNull() ?: 0)
    println(maxIndex)
}

fun tryHigherOrderFunction() {
    println(sum(1, "2", ::convertStringToInt))

    val number = convertStringToInt2()
    println(number("2"))
}

fun sum(a: Int, c: String, b: (String) -> Int): Int {
    return a + b(c)
}

fun convertStringToInt2(): (String) -> Int {
    return ::convertStringToInt
}

fun convertStringToInt(a: String): Int {
    a.toIntOrNull()?.let {
        return a.toInt()
    }
    return 0
}