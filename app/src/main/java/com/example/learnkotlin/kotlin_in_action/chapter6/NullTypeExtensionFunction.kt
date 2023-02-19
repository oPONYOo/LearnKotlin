package com.example.learnkotlin.kotlin_in_action.chapter6

// null이 될 수 있는 수신 객체에 대해 확장 함수 호출하기
// isNullOrBlank: null이 될 수 있는 타입의 확장 함수
fun main() {
    fun verifyUserInput(input: String?) {
        if (input.isNullOrBlank()) { // safety한 호출을 하지 않아도 된다.
            println("Please fill in the required fields")
        }
    }
    verifyUserInput(" ")
    verifyUserInput(null) // null을 수신 객체로 전달해도 예외가 발생하지 않는다.
}