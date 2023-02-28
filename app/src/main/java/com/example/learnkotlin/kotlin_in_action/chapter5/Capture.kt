package com.example.learnkotlin.kotlin_in_action.chapter5


fun printMessageWithPrefix(message: Collection<String>, prefix: String) {
    message.forEach {
        println("$prefix $it") // 자바와 다른점: 람다 밖 함수에 있는 파이널이 아닌(val가 아닌) 변수에 접근할 수 있다.
    }
}

var clientErrors = 0
fun printlnProblem(response: Collection<String>) {

    var serverErrors = 0
    response.forEach {
        if (it.startsWith("4")) {
            clientErrors++ // 람다 안에서 사용하는 외부 변수를 람다가 capture한 변수라고 부른다.
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")
}


class Ref<T>(var value: T) // 변경 가능한 변수(var)를 포획하는 방법을 보여주기 위한 클래스


fun main() {
    val errors = listOf("403 Forbidden", "404 Not fount")
    printMessageWithPrefix(errors, "Error: ")


    val responses = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error")
    printlnProblem(responses)


    // 변경 불가능한 변수를 capture하는 방법을 보여주기 위한 클래스
    val counter = Ref(0) // 클래스의 인스턴스에 대한 참조는 final(val)
    // 공식적으로는 변경 불가능한 변수(counter)를 capture 했지만 그 변수가 가리키는 객체의 필드 값을 바꿀 수 있다.
    val inc = { counter.value++ }


    // 실제 코드
    var realCounter = 0
    val realInc = { realCounter++ }
}


// 람다를 이벤트 핸들러 혹은 비동기적으로 실행되는 코드로 활용하는 경우
// 함수 호출이 끝난 다음에 로컬 변수가 변경될 수 있음
// 아래 함수는 항상 0을 반환한다.
// 핸들러는 clicks를 반환한 다음에 호출되기 때문
// 제대로 구현하려면 clicks 변수를 함수 내부가 아닌 클래스의 프로퍼티나 전역 프로퍼티 등의 위치로 빼내야함.
/*fun tryToCountButtonClicks(button: Button): Int {
    var clicks = 0
    button.onClick { clicks++ }
    return clicks
}*/