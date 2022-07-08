package com.example.myapplication.rxExample

import io.reactivex.rxjava3.core.Observable

fun main() {
    Observable.range(1,10)//(1)
        .filter{//(2) lọc chỉ cho output ra là số chẵn
            it%2==0
        }
        .subscribe {
            println("Received $it")
        }
}