package com.example.learnkotlin

import kotlin.random.Random

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        // 프로퍼티 게터 선언
        get() = height == width
}

fun createRandomRectangle(): Rectangle {
    val random = Random
    return Rectangle(random.nextInt(), random.nextInt())
}

fun main() {
    println(createRandomRectangle().isSquare)
}