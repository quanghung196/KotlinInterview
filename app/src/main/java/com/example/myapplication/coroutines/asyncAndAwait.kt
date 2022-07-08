package com.example.myapplication.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

suspend fun printOne(): Int {
    delay(1000L)
    return 10
}

suspend fun printTwo(): Int {
    delay(2000L)
    return 20
}

/*Không dùng async và await -> chạy tuần tự từ trên xuống dưới*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = printOne()
//        val two = printTwo()
//        println("The answer is ${one + two}")
//    }
//    println("Completed in $time ms")
//}

/*Không dùng async và await -> chạy song song*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = async { printOne() }
//        val two = async { printTwo() }
//        val three = async { printTwo() }
//        val four = async { printTwo() }
//        println("The answer is ${one.await() + two.await() + three.await() + four.await()}")
//    }
//    println("Completed in $time ms")
//}

/*Lazy async with .start() -> chạy song song*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = async(start = CoroutineStart.LAZY) { printOne() }
//        val two = async(start = CoroutineStart.LAZY) { printTwo() }
//        one.start() // start the first one
//        two.start() // start the second one
//        println("The answer is ${one.await() + two.await()}")
//    }
//    println("Completed in $time ms")
//}

/*Lazy async without .start() -> chạy tuần tự*/

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { printOne() }
        val two = async(start = CoroutineStart.LAZY) { printTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}