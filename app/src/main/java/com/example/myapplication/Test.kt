package com.example.myapplication

fun main() {
    var a: Int = 9
    val b: MutableList<Int> = mutableListOf(0,1,2)
    increaseByOne(b)
    print(b)
}

fun increaseByOne(x: MutableList<Int>) {
    x.add(4)
}