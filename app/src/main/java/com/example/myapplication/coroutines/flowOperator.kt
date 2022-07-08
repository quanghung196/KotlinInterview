import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/*
* Toán tử take() trong Flow
* */

//fun numbers(): Flow<Int> = flow {
//    try {
//        emit(1)
//        emit(2)
//        println("This line will not execute")
//        emit(3)
//    } catch (e: CancellationException) {
//        println("exception")
//    } finally {
//        println("close resource here")
//    }
//}
//
//fun com.example.myapplication.coroutines.main() = runBlocking {
//    numbers()
//        .take(2) // take only the first two
//        .collect { value -> println(value) }
//}

/*
* Toán tử transform() trong Flow
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    (1..9).asFlow() // a flow of requests
//        .transform { value ->
//            if (value % 2 == 0) { // Emit only even values, but twice
//                emit(value * value)
//                emit(value * value * value)
//            } // Do nothing if odd
//        }
//        .collect { response -> println(response) }
//}

/*
* Toán tử map() trong Flow chỉ cho emit 1 lần và không cho so sánh if
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    (1..5).asFlow()
//        .map {
//            it * it
//        } // squares of numbers from 1 to 5
//        .collect { println(it) }
//}

/*
* Toán tử filter() trong Flow
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    (1..5).asFlow()
//        .filter {
//            println("Filter $it")
//            it % 2 == 0
//        }.collect {
//            println("Collect $it")
//        }
//}

/*
* Toán tử onEach() trong Flow
* Toán tử này dùng khi ta muốn thực hiện một action gì đó trước khi value từ flow được emit.
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val nums = (1..3).asFlow().onEach { delay(3000) } // numbers 1..3 every 300 ms
//    val startTime = System.currentTimeMillis()
//    nums.collect { value ->
//        println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//    }
//}

/*
* Toán tử reduce() trong Flow
* Hàm reduce cực hữu ích khi chúng ta cần tính tổng cộng dồn tất cả giá trị được phát ra từ nguồn phát.
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val sum = (1..3).asFlow()
//        .map { it * it } // squares of numbers from 1 to 5
//        .reduce { a, b -> a * b } // sum them
//    println(sum)
//}

/*
* Toán tử fold() trong Flow
* Hàm fold giống hàm fold nhưng có một initial value.
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val sum = (1..3).asFlow()
//        .fold(initial = 10) { a, b -> // mình cho giá trị khởi tạo ban đầu là 10
//            println("Tổng đã tích lũy: $a đồng")
//            println("Giá trị mới: $b đồng")
//            a + b } // sum them (terminal operator)
//    println("Kết quả = $sum đồng")
//}

/*
* Toán tử toList(), toSet() trong Flow
* Toán tử này giúp chúng ta convert một flow thành một ArrayList hoặc LinkedHashSet
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val list: List<String> = listOf("a", "b", "c", "d", "e").asFlow().toList()
//    val set: Set<Int> = (1..5).asFlow().toSet()
//    println("${list.javaClass} $list")
//    println("${set.javaClass} $set")
//}

/*
* Toán tử first() trong Flow
* Toán tử này giúp chúng ta convert một flow thành một ArrayList hoặc LinkedHashSet
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val a: Int = listOf(1, 3, 5, 7, 2, 6, 8, 4).asFlow().first()
//    println(a)
//
//    val b: Int = listOf(1, 3, 5, 7, 2, 6, 8, 4).asFlow().first { it % 2 == 0 }
//    println(b)
//}

/*
* Toán tử single(), singleOrNull() trong Flow
* Toán tử single để check chắc chắn rằng nguồn flow chỉ có một phần tử và nó sẽ return giá trị đó.
* Trường hợp flow có nhiều hay ít hơn 1 phần tử đều bị throw Exception.
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val a: Int = listOf(10).asFlow().single() // trả về 10
//    //listOf(1, 2).asFlow().single() // throw IllegalStateException vì có nhiều hơn 1 phần tử
//    listOf<Int>().asFlow().single() // throw IllegalStateException vì có ít hơn 1 phần tử
//    println(a) // in ra 10
//}

/*
* Để tránh bị throw Exception chúng ta có thể sử dụng toán tử singleOrNull().
* Toán tử này sẽ trả về null nếu flow không có phần tử nào.
* Trường hợp flow có nhiều hơn một phần tử nó vẫn throw Exception như thường
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val a: Int? = listOf(10).asFlow().singleOrNull() // trả về 10
//    val b: Int? = listOf<Int>().asFlow().singleOrNull() // trả về null vì có ít hơn 1 phần tử
//    listOf(1, 2).asFlow().singleOrNull() //throw Exception vì có nhiều hơn 1 phần tử
//    println(a.toString()) // in ra 10
//    println(b.toString()) // in ra null
//}

/*
* Toán tử zip() trong Flow
* Toán tử này dùng để zip 2 flow lại (giống như hàm zip trong Sequence hay List).
* Có nghĩa là nó sẽ lấy 1 phần tử bên flowA và 1 phần tử bên flowB để kết hợp lại tạo ra một phần tử mới.
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val num = (1..4).asFlow() // numbers 1..3
//    val str = flowOf("one", "two", "three") // strings
//    num.zip(str) { a, b -> "$a -> $b" } // compose a single string
//        .collect { println(it) } // collect and print
//}

/*
* Toán tử combine() trong Flow
* Toán tử này dùng để zip 2 flow lại (giống như hàm zip trong Sequence hay List).
* Có nghĩa là nó sẽ lấy 1 phần tử bên flowA và 1 phần tử bên flowB để kết hợp lại tạo ra một phần tử mới.
* */

//fun com.example.myapplication.coroutines.main() = runBlocking {
//    val num = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
//    val str = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
//    val startTime = System.currentTimeMillis()
//    // remember the start time
//    num.zip(str) { a, b -> "$a -> $b" } // compose a single string with "zip"
//        .collect { value -> // collect and print
//            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//        }
//
//    num.combine(str) { a, b -> "$a -> $b" } // compose a single string with "combine"
//        .collect { value -> // collect and print
//            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//        }
//}

/*
* Toán tử flatMapConcat() trong Flow
* Toán tử này dùng để zip 2 flow lại (giống như hàm zip trong Sequence hay List).
* Có nghĩa là nó sẽ lấy 1 phần tử bên flowA và 1 phần tử bên flowB để kết hợp lại tạo ra một phần tử mới.
* */

fun requestFlow(i: Int): Flow<String> = flow { // Đây là flowB
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

fun emit(s: String) {

}

fun main() = runBlocking {
    //concat() sẽ đợi flowB đi xong hết mới tiếp tục flowA
    println("------------concat------------")
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapConcat { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }

    //merge() sẽ không đợi flowB đi xong hết mà tiếp tục flowA
    println("------------merge------------")
    val startTime2 = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapMerge { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime2} ms from start")
        }

    //latest() hủy hàm delay() trong flow B để chạy tiếp flowA
    //khi flowB đã duyệt đến cuối cùng thì gặp hàm delay() sẽ không bị hủy nữa
    println("------------latest------------")
    val startTime3 = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapLatest  { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime3} ms from start")
        }
}