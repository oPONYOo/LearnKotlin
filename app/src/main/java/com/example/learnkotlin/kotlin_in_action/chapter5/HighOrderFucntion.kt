package com.example.learnkotlin.kotlin_in_action.chapter5

fun main() {
    val people =
        listOf(Person("JINA", 24), Person("PONYO", 20), Person("Alice", 24), Person("Bob", 31))

    println(people.map { it.name })
    println(people.map(Person::name))
    println(people.filter { it.age > 22 }.map(Person::name))

    val maxAge = people.maxBy(Person::age).age
    println(people.filter { it.age == maxAge })

    val numbers = mapOf(0 to "zero", 1 to "one", 2 to "two")
    println(numbers.mapValues { it.value.uppercase() })

    val canBeInClub27 = { p: Person -> p.age <= 27 }
    println(people.all(canBeInClub27))  // all: 모든 원소가 술어를 만족하는지
    println(people.any(canBeInClub27)) // any: 술어를 만족하는 원소가 하나라도 있는지

//    println(people.filter(canBeInClub27).size)  조건을 만족하는 모든 원소가 들어가는 중간 컬렉션이 생기므로 비효율적
    println(people.count(canBeInClub27)) // count: 술어를 만족하는 원소의 개수. 개수만 추적하므로 효율적
    println(people.find(canBeInClub27)) // find: 술어를 가장 먼저 만족하는 원소 반환

    println(people.groupBy { it.age }) // groupBy: 원소를 구분하는 특성이 키이고 그에 따른 그룹이 값인 맵을 반환한다.

    val list = listOf("a", "ab", "b")
    println(list.groupBy(String::first))


    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() }) // flatMap: 여러 리스트를 한 리스르토 한데 모은다. [a,b,c], [d,e,f] -> [a,b,c,d,e,f]
    // toList() 함수를 문자열에 적용하면 그 문자열에 속한 모든 문자로 이뤄진 리스트가 만들어진다.

    val books = listOf(
        Book("Thursday Next", listOf("Jasper Fforde")),
        Book("Mort", listOf("Terry Pratchett")),
        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
    )
    println(books.flatMap { it.authors }.toSet()) // toSet: 중복을 없애고 집합으로 만든다.
}

class Book(val title: String, val authors: List<String>)