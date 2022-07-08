package com.example.myapplication.rxExample

import com.example.myapplication.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.io.Serializable


fun main() {
    val observable: Observable<Serializable> = createUserObservable()
    val observer: Observer<Serializable> = createUserObserver()

    observable
        //.subscribeOn(Schedulers.io())
        //.observeOn(Schedulers.io())
        .subscribe(observer)
}

private fun createUserObservable(): Observable<Serializable> {
    val userList = createListUser()
    return Observable.just(userList, "Bui Quang Hung")
}

private fun createUserObserver(): Observer<Serializable> {
    val observer = object : Observer<Serializable> {
        override fun onNext(serializable: Serializable) {
            println("onNext: $serializable\nRun on ${Thread.currentThread().name}")
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

private fun createListUser(): ArrayList<User> {
    val user: ArrayList<User> = arrayListOf()
    user.add(User(1, "One"))
    user.add(User(2, "Two"))
    user.add(User(3, "Three"))
    user.add(User(4, "Four"))
    user.add(User(5, "Five"))

    return user
}

