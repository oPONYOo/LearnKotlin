package com.example.learnkotlin.kotlin_in_action.chapter6


fun main() {
    // 컬렉션 생성함수
    // 구체적인 타입을 타입 인자로 넘기면 타입을 인스턴스화할 수 있다.
    val map = mapOf<String, Int>()
    val letters = Array<String>(26) { i -> ('a' + i).toString() } // 타입 명시 안해도 컴파일러가 자동으로 원소 타입 추론해줌
    println(letters.joinToString(""))

    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))
    // vararg 인자를 넘기기 위해 스프레드 연산자 사용

    val fourOnes = IntArray(4)
    val fourOnesToo = intArrayOf(1, 1, 1, 1) // IntArray생성

    val squares = IntArray(5) { i -> (i + 1) * (i + 1) }
    println(squares.joinToString())

    fun array(args: Array<String>) {
        args.forEachIndexed { index, element ->
            println("Argument $index is : $element")
        }
    }
    array(Array(5) { "jina" })
}