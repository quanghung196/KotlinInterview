package com.example.myapplication.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking { // scope 1
    launch {       // coroutine 1
        delay(200L)
        println("Task from runBlocking")   // line code 1
    }

    coroutineScope { // coroutine 2   // scope 2
        launch {   // coroutine 3
            delay(500L)
            println("Task from nested launch") // line code 2
        }

        delay(100L)
        println("Task from coroutine scope") // line code 3
    }

    println("Coroutine scope is over") // line code 4
}

/*Khi coroutine cha bị hủy, tất cả các con của nó cũng bị hủy theo*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val request = launch {
//        launch {
//            delay(100)
//            println("job2: I am a child of the request coroutine")   // line code 1
//            delay(1000)
//            println("job2: I will not execute this line if my parent request is cancelled") // line code 2
//        }
//    }
//    delay(500)
//    request.cancel() // cancel processing of the request
//    delay(1000)
//    println("com.example.myapplication.coroutines.main: Who has survived request cancellation?") // line code 3
//}

/*Global Scope*/

//fun com.example.myapplication.coroutines.main() = runBlocking<Unit> {
//    val request = launch {
//        // it spawns two other jobs, one with GlobalScope
//        GlobalScope.launch {
//            println("job1: GlobalScope and execute independently!")
//            delay(1000)
//            println("job1: I am not affected by cancellation")  // line code 1 này vẫn được in ra mặc dù bị delay 1000ms
//        }
//        // and the other inherits the parent context
//        launch {
//            delay(100)
//            println("job2: I am a child of the request coroutine")
//            delay(1000)
//            println("job2: I will not execute this line if my parent request is cancelled")
//        }
//    }
//    delay(500)
//    request.cancel() // cancel processing of the request
//    delay(1000) // delay a second to see what happens
//    println("com.example.myapplication.coroutines.main: Who has survived request cancellation?")
//}