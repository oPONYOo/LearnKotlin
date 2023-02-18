package com.example.learnkotlin.kotlin_in_action.chapter5

fun main() {
    val people =
        listOf(
            Person("JINA", 24),
            Person("PONYO", 20),
            Person("Alice", 24),
            Person("Bob", 31)
        )

    val startWithA = people.asSequence() // asSequence 확장함수를 호출하면 어떤 컬렉션이든 시퀀스로 바꿀 수 있다.
        .map(Person::name)
        .filter { it.startsWith("A") }
        .toList()

    println(startWithA)

    /*public inline fun <T, R> Iterable<T>.map(
        transform: (T) -> R
    ): List<R>*/

    /*public fun <T, R> Sequence<T>.map(
        transform: (T) -> R
    ): Sequence<R>*/

    // 시퀀스의 경우 모든 연산은 각 원소에 대해 순차적으로 적용된다.
    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { print("filter($it) "); it % 2 == 0 }
        .toList()
    // map(1) filter(1) map(2) filter(4) map(3) filter(9) map(4) filter(16)

    println(listOf(1, 2, 3, 4).asSequence()
        .map { it * it }.find { it > 3 })

    // 연산의 순서도 성능에 영향을 끼친다.
    // map -> filter
    println(people.asSequence().map(Person::name).filter { it.length < 4 }.toList())
    // map을 먼저 하면 모든 원소를 변환한다.

    // filter -> map
    println(people.asSequence().filter { it.name.length < 4 }.map(Person::name).toList())
    // filter를 먼저하면 부적절한 원소를 먼저 제외하기 때문에 그런 원소는 변환되지 않는다.

    // generateSequence 함수로 sequence를 만들 수도 있다.
    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())
}