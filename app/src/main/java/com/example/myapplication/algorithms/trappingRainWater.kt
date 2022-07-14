package com.example.myapplication.algorithms

var waterLevel: Int = 0

fun main() {
    val bricks = arrayListOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1, 0)
    val listBrick1: ArrayList<Int> = arrayListOf()
    val listBrick2: ArrayList<Int> = arrayListOf()

    val splitListBrick: (ArrayList<Int>, ArrayList<Int>, ArrayList<Int>) -> Unit =
        { lb1: ArrayList<Int>, lb2: ArrayList<Int>, b: ArrayList<Int> ->
            val maxIndex = findMaxIndex(arr = b)
            for (i in 0 until maxIndex) {
                lb1.add(b.first())
                b.removeFirst()
            }
            b.removeFirst()
            lb2.addAll(b)
            lb1.reverse()
        }
    splitListBrick(listBrick1, listBrick2, bricks)
    println(listBrick1)
    println(listBrick2)
    calculateRainWatterLevel(arr = listBrick1)
    calculateRainWatterLevel(arr = listBrick2)
    println("Water level = $waterLevel")
}

fun findMaxIndex(arr: ArrayList<Int>): Int = arr.indexOf(arr.maxOrNull() ?: 0)

fun calculateRainWatterLevel(arr: ArrayList<Int>) {
    if (arr.isEmpty()) return
    val maxIndex = findMaxIndex(arr)
    for (i in 0..maxIndex) {
        waterLevel += (arr[maxIndex - i] - arr.first())
        arr.removeFirst()
    }
    calculateRainWatterLevel(arr = arr)
}

