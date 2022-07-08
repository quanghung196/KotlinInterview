package com.example.myapplication.coroutines

import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/*Chạy song song code trong khối launch và ngoài khối launch, khi công việc ngoài khối launch kết
* thúc trước -> end chương trình và delete trương trình đang thực hiện trong khối launch*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    GlobalScope.launch { // chạy một coroutine trên background thread
//        delay(1000L) // non-blocking coroutine bị delay 10s
//        println("World,") // print từ World ra sau khi hết delay
//    }
//    println("Hello,") // com.example.myapplication.coroutines.main thread vẫn tiếp tục chạy xuống dòng code này trong khi coroutine vẫn đang bị delay 10s
//    Thread.sleep(500L) // block com.example.myapplication.coroutines.main thread 0.5s
//    println("Kotlin")
//}

/*---------------------------------------------------------------------------------------------------------------------------*/

/*Chạy song song code trong khối launch và ngoài khối launch*/

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    GlobalScope.launch { // chạy một coroutine trên background thread
//        delay(1000L) // non-blocking coroutine bị delay 10s
//        println("World,") // print từ World ra sau khi hết delay
//    }
//    println("Hello,") // com.example.myapplication.coroutines.main thread vẫn tiếp tục chạy xuống dòng code này trong khi coroutine vẫn đang bị delay 10s
//    Thread.sleep(2000L) // block com.example.myapplication.coroutines.main thread 2s
//    println("Kotlin")
//}

/*---------------------------------------------------------------------------------------------------------------------------*/

/*Chạy tuần tự -> runBlocking block com.example.myapplication.coroutines.main Thread*/

//fun com.example.myapplication.coroutines.main() {
//    runBlocking { // chạy một coroutine
//        println("Hello")
//        delay(2000)
//    }
//    println("World")
//}





/*---------------------------------------------------------------------------------------------------------------------------*/