package com.example.myapplication.rxExample

import com.example.myapplication.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


fun main() {
    val observable: Observable<User> = createUserObservable()
    val observer: Observer<User> = createUserObserver()

    observable
        .subscribeOn(Schedulers.io())
        //.observeOn(Schedulers.())
        .subscribe(observer)
}

private fun createUserObservable(): Observable<User> {
    val userList = createListUser()
    val observable: Observable<User> = Observable.create { emitter ->

        if (userList.isEmpty()) {
            if (!emitter.isDisposed) {
                emitter.onError(Exception())
            }
        }

        for (user in userList) {
            if (!emitter.isDisposed) {
                emitter.onNext(user)
            }
        }

        if (!emitter.isDisposed) {
            emitter.onComplete()
        }
    }

    return observable
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
            println("onError $e\nRun on ${Thread.currentThread().name})")
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