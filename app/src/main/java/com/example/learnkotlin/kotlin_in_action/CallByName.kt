package com.example.learnkotlin.kotlin_in_action

fun main() {
    fun callByName(f: () -> Boolean): Boolean {
        println("callByName")
        return f()
    }

    val funA: () -> Boolean = {
        println("funA")
        true
    }

    callByName(funA)
}