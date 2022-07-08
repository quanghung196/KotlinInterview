package com.example.myapplication.rxExample

import com.example.myapplication.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


fun main() {
    val user = User(id = 0, name = "Bui Quang Hung")

    val observable: Observable<User> = user.getUserDeferObservable()

    user.name = "bqhung196"

    val observer: Observer<User> = createUserObserver()

    observable
        //.subscribeOn(Schedulers.io())
        //.observeOn(Schedulers.io())
        .subscribe(observer)
}

private fun createUserObserver(): Observer<User> {
    val observer = object : Observer<User> {
        override fun onNext(user: User) {
            println("onNext: $user\nRun on ${Thread.currentThread().name}")
            println("---------------------------------------------------------------")
        }

        override fun onComplete() {
            println("onComplete\nRun on ${Thread.currentThread().name}")
            println("---------------------------------------------------------------")
        }

        override fun onError(e: Throwable) {
            println("onError $e\nRun on ${Thread.currentThread().name}")
            println("---------------------------------------------------------------")
        }

        override fun onSubscribe(s: Disposable) {
            println("onSubscribe\nRun on ${Thread.currentThread().name}")
            println("---------------------------------------------------------------")
        }
    }

    return observer
}

