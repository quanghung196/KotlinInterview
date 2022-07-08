package com.example.myapplication

data class Person(
    var name: String,
    var age: Int,
    var height: Int,
    var phone: String
) {

    override fun toString(): String {
        return "Person(name='$name', age=$age, height=$height, phone='$phone')"
    }
}