package com.example.learnkotlin.kotlin_in_action.chapter2



const val TAG = "StringTemplate"

fun main() {
    val size = 30
    println(TAG + " :: java style " + size)
    println("$TAG :: kotlin style $size")
}