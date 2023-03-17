package com.example.learnkotlin.kotlin_in_action.chapter9


fun main() {
    // slice 함수는 구체적 범위 안에 든 원소만을 포함하는 새로운 리스트를 반환한다.
    // 타입 파라미터 선언
//    fun <T> List<T>.slice(indices: IntRange): List<T>
    // 타입 파라미터 T가 수신 객체와 반환 타입에 쓰인다.
    //
    val letters = ('a'..'z').toList()
    println(letters.slice<Char>(0..2)) // 타입 인자 명시적으로 지정

    println(letters.slice(10..13)) // 컴파일러는 여기서 T가 Char라는 사실을 추론한다.

    val authors = listOf("JINA", "PONYO")
    val readers = mutableListOf<String>()

//    fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T>
    // 람다 파라미터에 대해 자동으로 만들어진 변수 it의 타입은 T라는 제네릭
    // 여기서 T는 함수 파라미터의 타입 (T) -> Boolean에서 온 타입이다.
    readers.filter { it !in authors }
    // 컴파일러 filter가 List<T>타입의 리스트에 대해 호출될 수 있다는 사실과 filter의 수신 객체인 reader의 타입이
    // List<String>라는 것으로 부터 T가 String임을 추론한다.

    println(listOf(1, 2, 3, 4).penultimate) // 이 호출에서 타입 파라미터 T는 Int로 추론된다.



}

// 제네릭 확장 프로퍼티를 선언할 수 있다.
// 마지막 원소 바로 앞에 있는 원소
val <T> List<T>.penultimate: T
    get() = this[size - 2]

// 일반 프로퍼티는 타입 파라미터를 가질 수 없다.
// 클래스 프로퍼티에 여러 타입의 값을 저장할 수 없기 때문
//val <T> x: T = TODO()
// error: Type parameter of a property must be used in its receiver type