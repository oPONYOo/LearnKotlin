package com.example.learnkotlin.kotlin_in_action.chapter7


// data 클래스의 주생성자에 있는 프로퍼티에 대해서는 컴파일러가 자동으로 componentN 함수를 만들어주지만
// 일반 클래스에서는 직접 구현
class GeneralPoint(val x: Int, val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}

data class NameComponent(val name: String, val extension: String)

fun main() {
    val p = Point(10, 20)
    val (x, y) = p
    /*int x = p.component1();
    int y = p.component2();*/
    // 구조 분해를 사용하면 복합적인 값을 분해해서 여러 다른 변수를 한꺼번에 초기화할 수 있다.
    // 구조 분해 선언의 각 변수를 초기화하기 위해 componentN이라는 함수 호출
    println(x)
    println(y)


    // 함수에서 여러 값을 반환할 때 유용하다.
    // 1. 반환할 값이 모두 들어있는 데이터 클래스 정의
    // 2. 함수의 반환 타입을 데이터 클래스로 설정
    // 3. 구조 분해 선언을 사용 -> 데이터 클래스를 분해해서 여러 변수에 넣기
    /*fun splitFilename(fullName: String): NameComponent {
        val result = fullName.split('.', limit = 2)
        return NameComponent(result[0], result[1])
    }*/



    fun splitFilename(fullName: String): NameComponent {
        val (name, extension) = fullName.split('.', limit = 2)
        // 배열이나 컬렉션을 다루는 경우 유용하게 사용 가능
        return NameComponent(name, extension)
    }
    val (name, ext) = splitFilename("jina.kt")
    println(name)
    println(ext)


    // map의 원소에 대해 이터레이션할 때 유용
    fun printEntries(map: Map<String, String>) {
        for ((key, value) in map) {
            println("$key -> $value")
        }
    }
    val map = mapOf("jina" to "jjina", "ponyo" to "pponyo")
    printEntries(map)
}