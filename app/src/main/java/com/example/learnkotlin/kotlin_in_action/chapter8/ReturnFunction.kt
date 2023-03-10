package com.example.learnkotlin.kotlin_in_action.chapter8

enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)


// UI 상태에 따라 어떤 연락처 정보를 표시할지 결정해야 할 필요가 있다면
// 연락처 목록 표시 로직과 연락처 필터링 UI를 분리하기 위해 연락처 목록을 필터링하는 술어 함수를 만드는 함수를 정의할 수 있다.
class ContactListFilters {
    var prefix: String = "" // 이름이나 성이 뭘로 시작하는지
    var onlyWithPhoneNumber: Boolean = false // 필요한 경우 전화번호가 연락처에 있는지

    fun getPredicate(): (Person) -> Boolean { // 함수를 반환하는 함수를 정의
        val startsWithPrefix = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }
        if (!onlyWithPhoneNumber) {
            return startsWithPrefix // 함수타입의 변수 반환
        }
        return { startsWithPrefix(it) && it.phoneNumber != null } //람다 반환
    }
}

data class Person(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?
)


fun main() {
    // 함수를 함수에서 반환
    // 프로그램의 상태나 다른 조건에 따라 달라질 수 있는 로직
    // 사용자가 선택한 배송 수단에 따라 배송비를 계산하는 방법이 달라질 수 있음
    // Order을 받아서 Double을 반환하는 함수를 반환
    fun getShippingCostCalculator(
        delivery: Delivery
    ): (Order) -> Double { // 함수를 반환하는 함수 선언
        if (delivery == Delivery.EXPEDITED) {
            return { order -> 6 + 2.1 * order.itemCount } // 함수에서 람다 반환
        }
        return { order -> 1.2 * order.itemCount }
    }

    val calculator = getShippingCostCalculator(Delivery.EXPEDITED) // 반환받은 함수를 변수에 저장
    println("Shipping costs ${calculator(Order(3))}") // 반환받은 함수 호출


    val contacts = listOf(Person("JINA", "KiM", "1234"),
        Person("PONYO", "KiM", "1234"))

    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        prefix = "J"
        onlyWithPhoneNumber = true
    }

    println(contacts.filter(
        contactListFilters.getPredicate() // getPredicate이 반환한 함수를 "filter"에게 인자로 넘긴다
    ))

}