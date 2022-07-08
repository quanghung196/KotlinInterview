package com.example.myapplication.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/*
* Tạo 1 flow thông qua hàm flowOf hoặc asFlow
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val data = flowOf(1,"abc", 3.4, "def")
//    data.collect { println(it) }
//
//    listOf(1, "abc", 3.4, "def").asFlow().collect { println(it) }
//}

