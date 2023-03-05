package com.example.learnkotlin.kotlin_in_action


fun main() {
    fun callByValue(b: Boolean): Boolean {
        println("callByValue")
        return b
    }

    val funA: () -> Boolean = {
        println("funA")
        true
    }

    callByValue(funA())

}