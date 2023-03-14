package com.example.learnkotlin.kotlin_in_action.chapter9


// type parameter constraint
// 클래스나 함수에 사용할 수 있는 타입 인자를 제한하는 기능
// 타입 파라미터 T에 대한 상한을 정하고 나면 T 타입의 값을 그 상한 타입의 값으로 취급할 수 있다.
// 타입 파라미터 뒤에 상한을 지정함으로써 제약을 정의할 수 있다.
fun <T: Number> onHalf(value: T): Double {
    return value.toDouble() / 2.0
}


fun <T: Comparable<T>> max(first: T, second: T): T {
    return if (first > second) first else second
}

fun main() {
    println(max("kotlin", "java"))

    println(onHalf(3))
}