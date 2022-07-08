package com.example.myapplication.coroutines

import emit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

//fun foo(): Flow<Int> = flow {
//    for (i in 1..3) {
//        Thread.sleep(100) // pretend we are computing it in CPU-consuming way
//        log("Emitting $i")
//        emit(i) // emit next value
//    }
//}.flowOn(Dispatchers.Default) // RIGHT way to change context for CPU-consuming code in flow builder
//
//fun main() = runBlocking {
//    foo().collect { value ->
//        log("Collected $value")
//    }
//}

fun foo(): Flow<Int> = flow {
    for (i in 3 downTo -3) {
        //println("3 / $i = ${3 / i}") // nơi xảy ra exception trong nguồn phát
        emit(i) // emit next value
    }
}

/*
* try/catch vẫn catch được Exception dù exception có xảy ra trong nguồn thu hay nguồn phát.
* */

//fun main() = runBlocking {
//    try {
//        foo().collect { value ->
//            println("VALUE = $value")
//        }
//    } catch (e: Throwable) {
//        println("Caught $e")
//    }
//
//    try {
//        foo().collect { value ->
//            println("VALUE = $value")
//        }
//    } catch (e: Throwable) {
//        println("Caught $e")
//    }
//}

//fun main() = runBlocking {
//    foo().catch { e -> emit("Caught $e") }
//        .collect { value -> println("VALUE = $value")
//        }
//}

//fun main() = runBlocking {
//    foo().onEach { value ->
//        println("3 / $value = ${3 / value}") // nơi xảy ra Exception
//    }.catch { e -> println("Caught $e") }
//        .collect()
//}

/*
* onCompletion cho biết khi nào flow kết thúc
* */

fun foo1(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking<Unit> {
    foo1()
        .onCompletion { println("Done") }
        .collect { value -> println(value) }
}