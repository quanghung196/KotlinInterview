package com.example.myapplication.rxExample

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    createObservable() //(1) Tạo Observable
        .debounce(200, TimeUnit.MILLISECONDS)//(2) debounce 200 MILLISECONDS
        .subscribe {
            println(it)//(3)
        }
}

fun createObservable(): Observable<String> =
    Observable.create {
        it.onNext("R")//(4)
        runBlocking { delay(100) }//(5) block thread current 100 MILLISECONDS
        it.onNext("Re")
        runBlocking { delay(110) }
        it.onNext("React")
        runBlocking { delay(120) }
        it.onNext("Reactiv")
        runBlocking { delay(130) }
        it.onNext("Reactive")
        runBlocking { delay(250) }//(6)
        it.onNext("Reactive P")
        runBlocking { delay(140) }
        it.onNext("Reactive Pro")
        runBlocking { delay(150) }
        it.onNext("Reactive Program")
        runBlocking { delay(160) }
        it.onNext("Reactive Programming")
        runBlocking { delay(300) }
        it.onNext("Reactive Programming in")
        runBlocking { delay(170) }
        it.onNext("Reactive Programming in Ko")
        runBlocking { delay(180) }
        it.onNext("Reactive Programming in Kotlin")
        runBlocking { delay(250) }
        it.onComplete()
    }