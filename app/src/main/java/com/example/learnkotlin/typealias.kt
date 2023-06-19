package com.example.learnkotlin

// 별칭
// Top Level에만 정의 가능하므로 누구나 접근가능
typealias type = String

fun printNumString(numString: type) = println(numString)

fun main() {
    printNumString("12345") // String
}