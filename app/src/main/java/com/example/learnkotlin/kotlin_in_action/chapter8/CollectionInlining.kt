package com.example.learnkotlin.kotlin_in_action.chapter8

data class PersonNameAge(val name: String, val age: Int)


fun main() {
    val people = listOf(PersonNameAge("JINA", 22), PersonNameAge("PONYO", 4))

    // 람다 사용
    // 코틀린의 filter
    // 따라서 filter 함수의 바이트코드는 그 함수에 전달된 람다 본문의 바이트코드와 함께 filter를 호출한 위치에 들어간다.
    println(people.filter { it.age < 20 })


    // 람다 사용안하기
    val result = mutableListOf<PersonNameAge>()
    for (person in people) {
        if (person.age < 20) result.add(person)
    }
    println(result)
}