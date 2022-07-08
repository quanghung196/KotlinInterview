package com.example.myapplication

import io.reactivex.rxjava3.core.Observable
import java.io.Serializable


class User(var id: Int, var name: String) : Serializable {
    override fun toString(): String {
        return "id: $id, name: $name"
    }

    fun getUserObservable(): Observable<User> {
        return Observable.just(User(id = id, name = name))
    }

    fun getUserDeferObservable(): Observable<User> {
        return Observable.defer { Observable.just(User(id = id, name = name)) }
    }
}