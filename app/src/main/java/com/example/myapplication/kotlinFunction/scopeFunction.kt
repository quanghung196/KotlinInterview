package com.example.myapplication.kotlinFunction

import com.example.myapplication.Person

fun main() {
    //tryLet()
    //tryRun()
    //tryWith()
    //tryApply()
    tryAlso()
}

fun tryAlso() {
    Person(name = "Hung", age = 24, height = 177, phone = "0903261998")
        .also { newPerson ->
            println(newPerson.toString())
            newPerson.age = 20
            newPerson.name = "Bqhung"
            newPerson.height = 180
            println(newPerson.toString())
        }

    val listString = mutableListOf("one", null, "three", "four", "five")
    listString
        .map { stringNumber -> stringNumber?.length }
        .filter { stringLength -> (stringLength ?: 0) > 3 }
        .also { result -> println(result) }
}

fun tryApply() {
    Person(name = "Hung", age = 24, height = 177, phone = "0903261998")
        .apply {
            println(toString())
            age = 20
            name = "Bqhung"
            height = 180
            println(toString())
        }

    val listString = mutableListOf("one", null, "three", "four", "five")
    listString
        .map { stringNumber -> stringNumber?.length }
        .filter { stringLength -> (stringLength ?: 0) > 3 }
        .apply { println(this) }
}

fun tryWith() {
    val person = Person(name = "Hung", age = 24, height = 177, phone = "0903261998")
    println(person.toString())

    with(person) {
        age = 20
        name = "Bqhung"
        height = 180
        println(toString())
    }

    val listString = mutableListOf("one", null, "three", "four", "five")
    with(listString
        .map { stringNumber -> stringNumber?.length }
        .filter { stringLength -> (stringLength ?: 0) > 3 }) {
        println(this)
    }
}

fun tryLet() {
    val person = Person(name = "Hung", age = 24, height = 177, phone = "0903261998")
    println(person.toString())

    person.let { newPerson ->
        newPerson.age = 20
        newPerson.name = "Bqhung"
        println(newPerson.toString())
    }

    val listString = mutableListOf("one", null, "three", "four", "five")
    listString
        .map { stringNumber -> stringNumber?.length }
        .filter { stringLength -> (stringLength ?: 0) > 3 }
        .let { result -> println(result) }
}

fun tryRun() {
    val person = Person(name = "Hung", age = 24, height = 177, phone = "0903261998")
    println(person.toString())

    person.run {
        age = 20
        name = "Bqhung"
        println(toString())
    }

    val listString = mutableListOf("one", null, "three", "four", "five")
    listString
        .map { stringNumber -> stringNumber?.length }
        .filter { stringLength -> (stringLength ?: 0) > 3 }
        .run { println(this) }
}