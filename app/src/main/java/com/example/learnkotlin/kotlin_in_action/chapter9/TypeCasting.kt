package com.example.learnkotlin.kotlin_in_action.chapter9

import com.example.learnkotlin.kotlin_in_action.chapter3.infixcall.list

// 값이 set, map이 아닌 list라는 사실을 확인하려면 * 스타 프로젝션을 사용
// 인자를 알 수 없는 제네릭 타입을 표현
fun printSum(c: Collection<*>) {
    val intList = c as? List<Int> // 컴파일러가 경고해줌 Unchecked cast: Collection<*> to List<Int>
        ?: throw IllegalArgumentException("List is expected")

    println(intList.sum())
}

// 컴파일 시점에 c 컬렉션이 Int값을 저장한다는 사실이 알려져있으므로 검사 가능
fun safePrintSum(c: Collection<Int>) {
    if (c is List<Int>) { // 올바른 검사
        println(c.sum())
    }
}

fun main() {
    printSum(listOf(1, 2, 3)) // 작동함.
//    printSum(setOf(1, 2, 3)) // set은 list가 아니므로 exception발생
//    printSum(listOf("a", "b", "c")) // as? 캐스팅은 성공하지만 나중에 다른 예외 발생
    safePrintSum(listOf(1, 2, 3))
}