package com.example.learnkotlin.kotlin_in_action.chapter6

class Employee(val name: String, val manager: Employee?)

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

fun main() {
    fun managerName(employee: Employee): String? =
        employee.manager?.name

    val ceo = Employee("Da Boss", null)
    val developer = Employee("Bob smith", ceo)
    println(managerName(developer))
    println(managerName(ceo))


    // safety한 호출 연산자를 연쇄적으로 사용할 수 있다.
    fun Person.countryName(): String = company?.address?.country ?: "Unknown"

    val person = Person("Jina", null)
    println(person.countryName())

    // 엘비스 연산자: null 대신 사용할 디폴트 값을 지정할 때 편리하게 사용할 수 있다.
    fun foo(s: String?) {
        val t: String = s ?: "" // 좌항 ?: 우항
    }

    fun strLenSafe(s: String?): Int = s?.length ?: 0
    println(strLenSafe("abc"))
    println(strLenSafe(null))

    // 코틀린에서 retrun, throw 등의 연산도 식이다.
    // 엘비스 연산자의 우항에 return, throw등의 연산을 넣을 수 있다.
    fun printShippingLabel(person: Person) {
        val address = person.company?.address
            ?: throw IllegalArgumentException("No address") // 주소가 없으면 예외 발생시키기
        with(address) {
            println(streetAddress)
            println("$zipCode $city, $country")
        }
    }

    val address = Address("Elsestr. 47", 80687, "Munchi", "Germany")
    val jetbrains = Company("JetBrains", address)
    val dPerson = Person("Dmitry", jetbrains)
    printShippingLabel(dPerson)

//    printShippingLabel(Person("jina", null))


    // safety call + let 함수: null이 될 수 있는 값을 null이 아닌 값만 인자로 받는 함수에 넘기는 경우
    fun sendEmailTo(email: String) {
        println("Sending email to $email")
    }
    var email: String? = "ponyo@example.com"
    email?.let { sendEmailTo(it) }
    email = null
    email?.let { sendEmailTo(it) }

}