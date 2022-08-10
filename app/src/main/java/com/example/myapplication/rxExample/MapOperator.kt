package com.example.myapplication.rxExample

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable


fun main() {
    val observable = listOf("one", "two", "three", "four", "five").toObservable()
    observable
        .map { number ->
            "Length of $number is ${number.length}"
        }
        .flatMap { string ->
            Observable.just(string.split(" ")[2])
        }
        .subscribe { println(it) }//(4)
}