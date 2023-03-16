package com.example.learnkotlin.kotlin_in_action.chapter9

// 코틀린의 타입소거로 인해 실행 시점에 제네릭 타입의 타입 인자 정보를 알 수 없다.
// 따라서 제네릭 클래스의 인스턴스가 있어도 그 인스턴스를 만들 때 사용한 타입 인자를 알아낼 수 없다.
// 제네릭 함수의 타입 인자도 마찬가지로 제네릭 함수가 호출되도 그 함수의 본문에서는 호출 시 쓰인 타입 인자를 알 수 없다.
//fun <T> isA(value: Any) = value is T
// error: Cannot check for instance of erased type: T



inline fun <reified T> isA(value: Any) = value is T
fun main() {
    println(isA<String>("abc"))
    println(isA<String>(123))
}