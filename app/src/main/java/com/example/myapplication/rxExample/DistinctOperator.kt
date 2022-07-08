package com.example.myapplication.rxExample

import io.reactivex.rxjava3.kotlin.toObservable

fun main() {
    listOf(1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9, 3, 10)//(1)
        .toObservable()//(2)
        .distinct()//(3)
        .subscribe { println("Received $it") }//(4)
}