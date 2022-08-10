package com.example.myapplication.rxExample

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable

fun main() {
    val observable = listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1).toObservable()

    observable.flatMap { number ->
        Observable.create<String> {
            it.onNext("The Number $number")
            it.onNext("number/2: ${number / 2}")
            it.onNext("number%2: ${number % 2}")
            it.onComplete()
        }
    }.subscribeBy(
        onNext = { item ->
            println("Received $item")
        },
        onComplete = {
            println("Complete")
        },
        onError = {

        }
    )
}