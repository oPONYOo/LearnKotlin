package com.example.learnkotlin.kotlin_in_action.chapter8

fun main() {
    val people = listOf(PersonNameAge("JINA", 22), PersonNameAge("PONYO", 4))
    fun lookForJina(people: List<PersonNameAge>) {
        for (person in people) {
            if (person.name == "JINA") { // 이름이 JINA일 경우 lookForJina함수로부터 반환
                println("Found!!")
                return
            }
        }
        println("JINA is not found") // 지냐 없으면 출력
    }

    lookForJina(people)


    // forEach에 전달된 람다에서 return사용
    // forEach에 넘긴 람다 안에 있는 return도 앞의 예제와 같은 의미
    fun lookForJina2(people: List<PersonNameAge>) {
        people.forEach {
            if (it.name == "JINA") {
                println("Found!!")
                return // 앞의 예제와 같이 lookForJina2 함수에서 반환
            }
        }
        println("JINA is not found")
    }
    // 람다 안에서 return을 사용하면 람다로부터만 반환되는 게 아니라
    // 그 람다를 호출하는 함수가 실행을 끝내고 반환된다.
    // 그렇게 자신을 둘러싸고 있는 블럭보다 더 바깥에 있는 다른 블럭을 반환하게 만드는
    // return문을 non-local return 이라고 부른다.
    // 이렇게 할수 있으려면 람다를 인자로 받는 함수가 인라인 함수인 경우일 때만 가능
    // forEach는 인라인 함수이므로 람다 본문과 함께 인라이닝된다.
    // 따라서 return 식이 바깥족 함수(lookForJina2)를 반환시키도록 쉽게 컴파일할 수 있다.


    lookForJina2(people)


    // 레이블을 통해 local return 사용하기
    // 람다 안에서 local return은 for 루프의 break와 비슷한 역할을 한다.
    // local return은 람다의 실행을 끝내고 람다를 호출했던 코드의 실행을 계속 이어간다.
    // local return과 non-local return을 구분하기위해 label을 사용해야한다.
    fun lookForJina3(people: List<PersonNameAge>) {
        people.forEach label@{ // 람다식 앞에 레이블을 붙인다.
            if (it.name == "JINA") return@label // 앞에서 정의한 label 참조
        }
        println("JINA might be somewhere") // 항상 출력
    }
    lookForJina3(people)

    // 람다를 인자로 받는 인라인 함수의 이름을 return 뒤에 레이블로 사용해도 된다.
    fun lookForJina4(people: List<PersonNameAge>) {
        people.forEach {
            if (it.name == "JINA") return@forEach
        }
        println("JINA might be somewhere") // 항상 출력
    }

    lookForJina4(people)



    // 무명 함수: 기본적으로 local return
    // 무명함수는 코드 블록을 함수에 넘길 때 사용할 수 있는 다른 방법
    fun lookForJina5(people: List<PersonNameAge>) {
        people.forEach(fun(person) {// 람다 식 대신 무명 함수를 사용한다.
            if (person.name == "JINA") return // return은 가장 가까운 함수를 가르키는데 이 위치에서 가장 가까운 함수는 무명 함수이다.
            println("${person.name} is not JINA")
        }
        )
        println("JINA might be somewhere") // 항상 출력
    }
    lookForJina5(people)


    // filter에 무명 함수 넘기기
    // 블록이 본문인 무명 함수는 반환 타입을 명시 해야한다.
    people.filter(fun(person): Boolean {
        return person.age < 20
    })


    // 식을 본문으로 하는 무명 함수의 반환 타입은 생략이 가능하다.
    people.filter(fun(person) = person.age < 20)
}