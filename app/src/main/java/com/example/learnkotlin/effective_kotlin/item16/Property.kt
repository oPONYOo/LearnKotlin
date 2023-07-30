package com.example.learnkotlin.effective_kotlin.item16

import java.util.Date

// 코틀린의 프로퍼티는 게터와 세터를 가질 수 있다.
// 프로퍼티는 동작이 아니라 상태를 나타내야한다.
class Property {

    // 파생 프로퍼티(derived property): var을 사용해서 만든 읽고 쓸 수 있는 프로퍼티.
    var name: String? = null
        // backing field: 프로퍼티의 데이터를 저장해둔다.
        // 세터와 게터의 디폴트 구현에 사용되므로, 따로 만들지 않아도 디폴트로 생성됨.
        // val로 선언한 읽기 전용 프로퍼티에서는 백킹필드가 만들어지지 않음.
        get() = field?.uppercase()
        set(value) {
            if (!value.isNullOrBlank()) {
                field = value
            }
        }

    var mills: Long = 0
    var date: Date
        get() = Date(mills)
        set(value) {
            mills = value.time
        }




}

fun main() {
    val property = Property()
    property.name = "hello"
    println(property.name)

}