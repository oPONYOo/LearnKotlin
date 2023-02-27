package com.example.learnkotlin.kotlin_in_action.chapter7

// expando object(확장 가능한 객체)
// 자신의 프로퍼티를 동적으로 정의할 수 있는 객체를 만들 때 위임 프로퍼티 활용하는 경우
// ex) 임의의 정보(달라질 수 있음) + 필수정보
// 정보를 모두 맵에 저장, 맵을 통해 처리하는 프로퍼티를 통해 필수 정보를 제공하는 방법
class PersonMap {
    // 추가 정보
    private val _attribute = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attribute[attrName] = value
    }

    // 필수 정보
    /*val name: String
    get() = _attribute["name"]!! // 수동으로 맵에서 정보를 꺼냄*/

    val name: String by _attribute
    // 어떻게 작동??
    // 표준 라이브러라기 Map과 MutableMap 인터페이스에 대해 getValue와 setValue 확장함수를 제공하기 때문
    // getValue에서 맵에 프로퍼티 값을 저장할 때는 자동으로 프로퍼티 이름을 키로 활용한다.

}

fun main() {
    val p = PersonMap()
    val data = mapOf("name" to "jina", "home" to "seoul")
    for ((attrName, value) in data)
        p.setAttribute(attrName, value)
    println(p.name)
}