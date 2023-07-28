package com.example.learnkotlin.thejoyofkotlin.chapter1


// 치환 모델(substitution model): 참조 투명한 식을 결과값으로 치환해 프로그램을 계산하는 모델
//
fun main() {
    val x = add(mult(2, 3), mult(4, 5))
    // == val z = add(6, 20)
    // 각각의 반환 값으로 치환해도 프로그램 전체의 의미는 바뀌지 않는다.
}

fun add(a: Int, b: Int): Int {
    log(String.format("Returning ${a + b} as the result of $a + $b"))
    return a + b
}

fun mult(a: Int, b: Int) = a * b

fun log(m: String) {
    println(m)
}