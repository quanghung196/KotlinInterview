package com.example.myapplication.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    val job = launch {
//        for (x in 1..5) {
//            channel.send(x * x)
//        }
//    }
//    // print 5 giá trị, trước khi nhận cho delay 1s
//    delay(1000) // delay 1s
//    println(channel.receive()) // nhận giá trị thứ 1
//    delay(1000) // delay 1s
//    println(channel.receive()) // nhận giá trị thứ 2
//    delay(1000) // delay 1s
//    println(channel.receive()) // nhận giá trị thứ 3
//    delay(1000) // delay 1s
//    println(channel.receive()) // nhận giá trị thứ 4
//    delay(1000) // delay 1s
//    println(channel.receive()) // nhận giá trị thứ 5
//    println("Done! Channel is empty?: ${channel.isEmpty} / Coroutine is completed?: ${job.isCompleted} / Coroutine is active?: ${job.isActive}")
//}

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    val job = launch {
//        for (x in 1..5) {
//            channel.send(x * x)
//        }
//    }
//
//    println("Done! Coroutine is completed?: ${job.isCompleted} / Coroutine is active?: ${job.isActive}")
//}

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    val job = launch {
//        for (x in 1..5) {
//            channel.send(x * x)
//        }
//    }
//
//    for (x in 1..3) { // 3 vé được nhận
//        println(channel.receive())
//    }
//    println("Done! Coroutine is completed?: ${job.isCompleted} / Coroutine is active?: ${job.isActive}")
//}

//fun main() = runBlocking { // COROUTINE NÀY MÌNH ĐẶT TÊN LÀ "run blocking cô rơ tin"
//    val channel = Channel<Int>()
//    val job = launch {
//        for (x in 1..5) {
//            channel.send(x * x)
//        }
//    }
//
//    for (x in 1..10) {
//        println("Coroutine is completed?: ${job.isCompleted} / Coroutine is active?: ${job.isActive}")
//        println(channel.receive())
//    }
//    println("Done! Run blocking coroutine is active?: $isActive")
//}

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        for (x in 1..5) channel.send(x * x)
//        channel.close() // we're done sending
//    }
//    // here we print received values using `for` loop (until the channel is closed)
//    for (value in channel) println(value)
//    println("Done!")
//}


/*
Nếu channel đã close nhưng vẫn cố gắng receive thì sẽ throw ClosedReceiveChannelException
* */

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        for (x in 1..5) channel.send(x * x)
//        channel.close() // sau khi send xong 5 phần tử thì close
//    }
//    for (y in 1..10) println(channel.receive()) // send có 5 mà nhận tới 10
//    println("Done!")
//}

/*
Nếu channel đã close nhưng vẫn cố gắng send thì sẽ throw ClosedReceiveChannelException
* */

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        for (i in 1..10) {
//            if (i == 5) channel.close() // nếu i = 5 thì close đi, ko được send nữa.
//            channel.send(i * i) // nhưng ta vẫn cố send i = 5 -> throw ClosedSendChannelException
//        }
//    }
//    for (y in 1..5) {
//        println(channel.receive())
//    }
//    println("Done!")
//}

/*
* Buffer Channel -> buffer = capacity = 4 thì mới bị suspend
* */

//fun main() = runBlocking {
//    val channel = Channel<Int>(capacity = 4) // tạo ra 1 buffered channel với capacity = 4
//    val sender = launch {
//        // launch 1 coroutine để send data
//        repeat(10) { // send 10 data
//            channel.send(it) // hàm send sẽ bị suspend khi buffer is full
//            println("Sending $it") // in ra sau khi send
//        }
//    }
//
//    // cố ý ko nhận data để xem thằng send có bị suspend ko
//    delay(1000)
//    sender.cancel() // cancel sender coroutine
//}

/*
Conflated channel Là 1 buffered channel nhưng capacity chỉ bằng 1.
Thằng này khác thằng bufferd channel ở chỗ mặc dù chỉ có capacity = 1
nhưng khi full nó ko suspend thằng coroutine sender mà cho phép thằng sender tiếp tục send value,
giá trị mới nhất sẽ overwrite lên giá trị cũ.
Tức là lúc nào trong channel cũng chỉ có tối đa 1 giá trị mà thôi.
*/

//fun main() = runBlocking {
//    val channel = Channel<Int>(Channel.CONFLATED)
//    val sender = launch {
//        repeat(5) { // send 5 giá trị
//            println("Sending $it")
//            channel.send(it)
//        }
//    }
//    delay(1000) // delay 1s để nó send đủ cả 5 giá trị rồi mới nhận
//    channel.consumeEach { println("item = $it") } // in ra tất cả giá trị nhận được
//    sender.cancel() // cancel sender coroutine
//}

/*
*  Nó là 1 buffered channel nhưng capacity = vô tận.
*  Khác với buffered channel lưu trữ buffered data trong 1 Array thì Unlimited channel lưu trữ data trong 1 LinkedList.
*  Vì là List nên nó lưu trữ vô hạn, tất nhiên khi hết memory để lưu trữ thì nó sẽ throw OutOfMemoryException.
* */

fun main() = runBlocking {
    val channel = Channel<Int>(Channel.UNLIMITED)
    val sender = launch {
        repeat(7) { // send 7 data
            channel.send(it)
        }
    }
    delay(1000)
    // cố tình delay 1s để coroutine receiver ko thể receive value. Xem coroutine sender có bị suspend ko?

    repeat(7) { // nhận 7 data
        val value = channel.receive()
        println("number $value")
    }
    sender.cancel() // cancel sender coroutine
}