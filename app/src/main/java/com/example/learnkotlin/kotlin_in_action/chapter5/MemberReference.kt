package com.example.learnkotlin.kotlin_in_action.chapter5


// val getAge = {person: Person -> person.age }

//참조 대상이 함수인지 프로퍼티인지와는 관계 없이 멤버 참조 뒤에 괄호를 넣으면 안된다.
val getAge = Person::age // 클래스::멤버

fun salute() = println("salute!") // 최상위 함수



fun main() {
    run(::salute) // 최상위 함수 참조. 최상위 함수이므로 ::앞에 클래스 이름을 생략한다.
}