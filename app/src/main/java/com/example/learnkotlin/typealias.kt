package com.example.learnkotlin

// 별칭
// Top Level에만 정의 가능하므로 누구나 접근가능
typealias numStringType = String

fun printNumStringWithTypealias(numString: numStringType) = println(numString)


// Top Level에만 정의 가능
// only val
// Inline class must have exactly one primary constructor parameter
@JvmInline
value class NumStringType(val value: String)

fun printNumStringWithInlineClass(numString: NumStringType) = println(numString.value)


fun main() {
    printNumStringWithTypealias("12345") // String


    printNumStringWithInlineClass(NumStringType("56789"))
}

// 둘다 이름을 명확하게 표현하는게 중요
