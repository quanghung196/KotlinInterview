package com.example.myapplication.algorithms

fun main() {
    val string = "Bui Quang Hung hung"
    val map = HashMap<String, Int>()

    for (i in string) {
        val letter = i.toString()
        if (letter.isNotBlank()){
            var count = map[letter] ?: 1
            if (map.containsKey(letter)) {
                count++
            }
            map[letter] = count
        }
    }
    println(map)
}