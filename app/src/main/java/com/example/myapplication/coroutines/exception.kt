package com.example.myapplication.coroutines

import kotlinx.coroutines.*
import java.io.IOException
import java.lang.IndexOutOfBoundsException

/*launch { } gặp Exception thì throw luôn, còn async { }
 khi gặp Exception thì nó đóng gói Exception đó vào biến deferred.
 Chỉ khi biến deferred này gọi hàm await() thì Exception mới được throw ra.*/

//suspend fun com.example.myapplication.coroutines.main() {
//    runBlocking {
//        GlobalScope.launch {
//            println("Throwing exception from launch")
//            throw IndexOutOfBoundsException()
//            println("Unreached")
//        }
//    }
//
//    val deferred = GlobalScope.async {
//        println("Throwing exception from async")
//        throw ArithmeticException()
//        println("Unreached")
//    }
//
//    val deferred2 = GlobalScope.async {
//        println("Throwing exception from async")
//        throw ArithmeticException()
//        println("Unreached")
//    }
//    deferred2.await()
//}

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    GlobalScope.launch {
//        try {
//            println("Throwing exception from launch")
//            throw IndexOutOfBoundsException()
//            println("Unreached")
//        } catch (e: IndexOutOfBoundsException) {
//            println("Caught IndexOutOfBoundsException")
//        }
//    }
//
//    val deferred = GlobalScope.async {
//        println("Throwing exception from async")
//        throw ArithmeticException()
//        println("Unreached")
//    }
//    try {
//        deferred.await()
//        println("Unreached")
//    } catch (e: ArithmeticException) {
//        println("Caught ArithmeticException")
//    }
//}

//suspend fun com.example.myapplication.coroutines.main() {
//    val handler = CoroutineExceptionHandler { _, exception ->
//        println("Caught $exception")
//    }
//    val job = GlobalScope.launch(handler) {
//        throw AssertionError()
//    }
//    val job2 = GlobalScope.launch(handler) {
//        throw ArithmeticException()
//    }
//    val deferred = GlobalScope.async(handler) {
//        throw IndexOutOfBoundsException() // Nothing will be printed, relying on user to call deferred.await()
//    }
//
//    joinAll(job, job2, deferred)
//}

/*
    Như chúng ta đã biết, khi coroutine bị stop thì nó sẽ cố chạy code trong khối finally.
    Nếu như code trong khối finally cũng throw Exception thì sao??.
    Khi đó các tất cả Exception xảy ra trong tất cả khối finally sẽ bị suppressed.
    Chúng ta có thể in tất cả chúng ra bằng hàm exception.getSuppressed()
*/

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception with suppressed ${exception.suppressed.contentToString()}")
    }
    val job = GlobalScope.launch(handler) {
        launch {
            try {
                delay(Long.MAX_VALUE) // delay vô hạn
            } finally {
                throw ArithmeticException()
            }
        }
        launch {
            try {
                delay(Long.MAX_VALUE) // delay vô hạn
            } finally {
                throw IndexOutOfBoundsException()
            }
        }
        launch {
            delay(100)
            throw IOException()
        }
        delay(Long.MAX_VALUE)
    }
    job.join()
}