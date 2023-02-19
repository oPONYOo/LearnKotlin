package com.example.learnkotlin.kotlin_in_action.chapter6

class SafePerson(val firstName: String, val lastName: String) {
    override fun equals(o: Any?): Boolean {
        val otherPerson = o as? SafePerson ?: return false // 타입이 서로 일치하지 않으면 false 반환

        return otherPerson.firstName == firstName && // safe 캐스트를 하고나면 otherPerson이 SafePerson타입으로 스마트 캐스트된다.
                otherPerson.lastName == lastName
    }

    override fun hashCode(): Int =
        firstName.hashCode() * 37 + lastName.hashCode()
}

fun main() {
    val p1 = SafePerson("Jina", "Kim")
    val p2 = SafePerson("Jina", "Kim")
    println(p1 == p2) // == 연산자는 equals 메서드를 호출한다.
    println(p1.equals(42))
}