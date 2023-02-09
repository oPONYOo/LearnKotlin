package com.example.learnkotlin.extension

// 확장 프로퍼티 선언하기
val String.lastChar: Char
    get() = get(length - 1)

// 변경 가능한 확장 프로퍼티 선언하기
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

fun main() {
    println("JINA".lastChar)
    val sb = java.lang.StringBuilder("JINA?")
    sb.lastChar = '!'
    println(sb)

    /*
    자바
    현재파일이름.getLastChar("JINA");
    코틀린의 확장 프로퍼티를 자바에서 쓰려면 위와 같이 게터나 세터를 명시적으로 호출해야함.
     */
}