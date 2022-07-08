package com.example.myapplication.coroutines

import kotlinx.coroutines.*

/*---------------------------------------------------------------------------------------------------------------------------*/

/*Sample job.join*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
//        delay(2000L)
//        println("World!")
//    }
//    println("Hello,")
//    job.join() // wait until child coroutine completes
//    println("Kotlin")
//}

/*---------------------------------------------------------------------------------------------------------------------------*/

/*Sample job.cancel*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val job = launch {
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//    }
//    delay(4000L) // delay a bit
//    println("com.example.myapplication.coroutines.main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    println("com.example.myapplication.coroutines.main: Now I can quit.")
//}

/*Do không có hàm delay trong coroutineScope nên không thể check được job isActive value*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (i < 5) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++} ...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L) // delay a bit
//    println("com.example.myapplication.coroutines.main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    println("com.example.myapplication.coroutines.main: Now I can quit.")
//}

/*Không có hàm delay nhưng có thể cancel = job isActive*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (isActive) {   // Điều kiện i < 5 đã được thay bằng isActive để ngăn chặn coroutine khi nó đã bị hủy
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++} ...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L) // delay a bit
//    println("com.example.myapplication.coroutines.main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    println("com.example.myapplication.coroutines.main: Now I can quit.")
//}

/*Nếu tiến trình của một coroutine bị hủy bỏ thì ngay lập tức nó sẽ tìm đến khối finally để chạy code trong đó.
 Chúng ta có thể sử dụng đặc điểm này để tranh thủ close hết các resource trước khi coroutine đó chính thức bị khai tử */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            // Tranh thủ close resource trong này đi nha :D
//            println("I'm running finally")
//        }
//    }
//    delay(1300L) // delay a bit
//    println("com.example.myapplication.coroutines.main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    println("com.example.myapplication.coroutines.main: Now I can quit.")
//}

/*Coroutine vẫn có thể chết trong khối finally khi bị check lại biến isActive*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            println("I'm running finally")
//            delay(1000L)                      // hàm delay được thêm vào khối finally
//            println("Print me please!")
//        }
//    }
//    delay(1300L) // delay a bit
//    println("com.example.myapplication.coroutines.main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    println("com.example.myapplication.coroutines.main: Now I can quit.")
//}

/*Làm cho coroutine bất tử = withContext(NonCancellable*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            withContext(NonCancellable) {
//                // Nhờ có em NonCancellable mà anh được phép chạy bất chấp đấy
//                println("I'm running finally")
//                delay(1000L)
//                println("I'm non-cancellable")
//            }
//        }
//    }
//    delay(1300L) // delay a bit
//    println("com.example.myapplication.coroutines.main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    println("com.example.myapplication.coroutines.main: Now I can quit.")
//}

/*Hẹn giờ chết cho coroutine = withTimeoutOrNull*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val result = withTimeoutOrNull(1300L) {
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//        "Done" // will get cancelled before it produces this result
//    }
//    println("Result is $result")                // Biến result sẽ null
//}

/*
 Như chúng ta đã biết, khi một coroutine con xảy ra Exception thì các coroutine con khác bị stop.
 Nếu chúng ta không muốn điều này, cái chúng ta muốn là khi coroutine con xảy ra Exception thì các coroutine khác
 vẫn tiếp tục chạy và khi UI bị destroyed thì nó mới dừng.
 Khi đó, chúng ta có thể sử dụng SupervisorJob() thay vì Job()
*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val supervisor = SupervisorJob()
//    with(CoroutineScope(coroutineContext + supervisor)) {
//        // launch the first child -- its exception is ignored for this example (don't do this in practice!)
//        val firstChild = launch(CoroutineExceptionHandler { _, _ -> }) {
//            println("First child is failing")
//            throw AssertionError("First child is cancelled")
//        }
//        // launch the second child
//        val secondChild = launch {
//            firstChild.join()
//            // Cancellation of the first child is not propagated to the second child
//            println("First child is cancelled: ${firstChild.isCancelled}, but second one is still active")
//            try {
//                delay(Long.MAX_VALUE)
//            } finally {
//                // But cancellation of the supervisor is propagated
//                println("Second child is cancelled because supervisor is cancelled")
//            }
//        }
//        // wait until the first child fails & completes
//        firstChild.join()
//        println("Cancelling supervisor")
//        supervisor.cancel()
//        secondChild.join()
//    }
//}

/*
 Thay vì sử dụng SupervisorJob() chúng ta có thể sử dụng supervisorScope để launch coroutine
 thì tác dụng nó cũng tương tự như SupervisorJob().
 */

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    supervisorScope {
        val first = launch(handler) {
            println("Child throws an exception")
            throw AssertionError()
        }
        val second = launch(handler) {
            delay(100)
            throw AssertionError()
        }
        val third = launch(handler) {
            delay(100)
            println("Scope is completing")
        }
    }
    println("Scope is completed")
}