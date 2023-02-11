package com.example.learnkotlin.infixcall

val map = mapOf(1 to "one", 2 to "two", 3 to "three")
val list = listOf(1, 2, 3, 4, 5)


fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
}


fun main() {
    val one = 1.to("one") // 일반 호출
    val two = 2 to "two" // 중위 호출
    // to는 Pair 인스턴스를 반환한다.

    // 구조 분해 선언
    val (number, name) = 1 to "one"
    // number: Int
    // name: String

    for ((index, element) in list.withIndex()) println("$index: $element")
}