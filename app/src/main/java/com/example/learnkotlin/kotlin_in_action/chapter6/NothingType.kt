package com.example.learnkotlin.kotlin_in_action.chapter6

// Nothing 타입은 함수가 정상적으로 끝나지 않을 경우를 나타낼 때 유용하다.
fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

fun main() {
//    fail("Error occurred")

    val a = Address("Elsestr. 47", 80687, "Munchi", "Germany")
    val company = Company("hihi", a)

//  Nothing을 반환하는 함수를 엘비스 연산자의 우항에 사용해서 전제 조건(precondition)을 검사할 수 있다.
    val address = company.address ?: fail("No address")
    println(address.city)
}