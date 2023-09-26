package com.example.learnkotlin.thejoyofkotlin.chapter2

fun main() {
    // trimMargin 함수는 파라미터로 문자열 받고, 이 문자열은 정렬에 사용할 위치를 지정할 때 사용
    // 기본 값은 | 이지만 직접 지정도 가능
    println(
        """This is first line
        |and this is the secod one. """.trimMargin()
    )
}