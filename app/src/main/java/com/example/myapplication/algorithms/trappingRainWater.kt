package com.example.myapplication.algorithms

var waterLevel: Int = 0

fun main() {
    val listBrick1: ArrayList<Int> = arrayListOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1, 0)
    val listBrick2: ArrayList<Int> = arrayListOf()

    val splitListBrick: (ArrayList<Int>, ArrayList<Int>) -> Unit =
        { lb1: ArrayList<Int>, lb2: ArrayList<Int> ->
            val maxIndex = findMaxIndex(arr = lb1)
            for (i in 0 until maxIndex) {
                lb2.add(lb1.first())
                lb1.removeFirst()
            }
            lb1.removeFirst()
            lb2.reverse()

            println("List brick 1: $listBrick1")
            println("List brick 2: $listBrick2")
        }

    splitListBrick(listBrick1, listBrick2)

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

