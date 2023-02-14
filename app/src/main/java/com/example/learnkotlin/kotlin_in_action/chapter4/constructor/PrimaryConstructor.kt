package com.example.learnkotlin.kotlin_in_action.chapter4.constructor



// 아래 두 가지 상황에서 primary 생성자의 파라미터를 참조할 수 있다.
// 1. 초기화 블럭 안에서만 primary 생성자의 파라미터를 참조 가능
// 2. 프로퍼티를 초기화하는 식에서 참조 가능
class User1 constructor(_nickname: String) {
    private val nickname1: String

    init {
        nickname1 = _nickname  // 1.
    }
    // 초기화 블럭으로 객체가 인스턴스화될 때 실행된다.
    // 초기화 블럭은 primary constructor와 함께 사용된다.
    // primary constructor에는 별도의 코드를 포함할 수 없기 때문
}

class User2(_nickname: String) {
    val nickname = _nickname  // 2/
}



// primary 생성자의 파라미터로 프로퍼티를 초기화한다면 파라미터 이름 앞에 val을 추가하여 프로퍼티 정의와 초기화를 할 수 있다.
// val은 해당 파라미터에 상응하는 프로퍼티가 생성된다는 뜻
// 다폴트 값 정의 가능
open class User3(val nickname: String = "jina", val isSubscribed: Boolean = true)

//base 클래스를 초기화 하려면 클래스의 주 생성자에서 base 클래스의 생성자를 호출하고, 생성자 인자를 넘겨야 한다.
class TwitterUser(nickname: String) : User3(nickname) {}


open class PrimaryButton // 컴파일러가 자동으로 인자가 없는 디폴트 생성자 만들어줌

class RadioButton : PrimaryButton()  // PrimaryButton 클래스를 상속한 하위 클래스는 반드시 생성자를 호출해야한다.

class Secretive private constructor() // 외부에서 인스턴스화 불가능

fun main() {
    val jina = User3("지냐")
    println(jina.isSubscribed)
    val ponyo = User3("포뇨", isSubscribed = false)
    println(ponyo.isSubscribed)
}

