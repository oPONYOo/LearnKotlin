package com.example.learnkotlin.kotlin_in_action.chapter5

data class Person(val name: String, val age: Int)


fun main() {
    val people = listOf(Person("JINA", 24), Person("PONYO", 5))
    println(people.maxBy { it.age })
    println(people.maxBy(Person::age)) // 함수나 프로퍼티를 반환하는 역할을 수행하는 람다는 멤버 참조로 대치할 수 있다.

    people.maxBy({ p: Person -> p.age }) // 중괄호 안에 있는 코드는 람다 식이고 그 람다 식을 maxBy 함수에 넘긴다.
    // 여기서 람다 식은 Person 타입의 값을 인자로 받아서 인자의 age를 반환한다.

    // 위의 람다 식 개선하기
    // 1. 맨 뒤에 있는 인자가 람다 식이라면 람다를 괄호 밖으로 뺄 수 있다.
    people.maxBy() { p: Person -> p.age }

    // 2. 람다가 어떤 함수의 유일한 인자이고, 괄호 뒤에 람다를 썼다면 호출할 때 빈 괄호를 없애도 된다.
    people.maxBy { p: Person -> p.age }

    // 3. 컴파일러는 람다 파라미터의 타입 추론이 가능하다. -> 명시할 필요 없음
    people.maxBy { p -> p.age }

    // 4. 람다의 파라미터 이름을 디폴트 이름인 it으로 바꾸면 더 간단하게 쓸 수 있다.
    people.maxBy { it.age } // 중첩되는 람다일 경우 파라미터를 명시적으로 선언하는게 좋다.

    // 람다를 변수에 저장할 때는 파라미터의 타입을 추론할 문맥이 존재하지 않으므로 타입을 명시해야 한다.
    val getAge = { p: Person -> p.age }
    people.maxBy(getAge)


    // val names = people.joinToString(separator = " ", transform = { p: Person -> p.name }) // 마지막 인자를 함수로 받음 -> 괄호 밖으로 뺄 수 있음.
    val names = people.joinToString(" ") { p: Person -> p.name } // 간결하지만 람다의 용도를 정확히 알 수 없다.
    println(names)

    val sum = { x: Int, y: Int -> x + y } // 화살표가 인자 목록과 본문을 구분해준다.
    println(sum(1, 2))
    run { println(42) } //run은 인자로 받은 람다를 실행시켜주는 라이브러리 함수


    // 본문이 여러 줄인 경우 맨 마지막에 있는 식이 람다의 결과 값이 된다.
    val anotherSum = { x: Int, y: Int ->
        println("Computing the sum of $x and $y")
        x + y
    }
    println(anotherSum(1, 2))
}