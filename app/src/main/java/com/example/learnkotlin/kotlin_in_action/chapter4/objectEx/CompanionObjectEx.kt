package com.example.learnkotlin.kotlin_in_action.chapter4.objectEx

import com.example.learnkotlin.kotlin_in_action.chapter4.getFaceBookName

class A {
    companion object { // companion를 붙히면 해당 클래스의 동반 객체로 만들 수 있다.
        fun bar() {
            println("Companion object called")
        }
    }
}

class UseConstructorUser {
    val nickname: String

    constructor(email: String) {  //secondary 생성자
        nickname = email.substringBefore('@')
    }

    constructor(facebookAccountId: Int) { //secondary 생성자
        nickname = getFaceBookName(facebookAccountId)
    }
}

// secondary 생성자 -> 팩토리 메서드 사용해서 인스턴스 만들기
class UseFactoryUser private constructor(val nickname: String) { // primary 생성자 비공개로 지정 -> 생성자로 인스턴스화 불가능

    companion object { // 동반 객체 선언(이름 선언 가능)

        fun newSubscribingUser(email: String) = UseFactoryUser(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) = UseFactoryUser(getFaceBookName(accountId))
        // 페이스북 사용자 아이디로 UseFactoryUser를 만드는 팩토리 메서드
    }
}

interface JSONFactory<T> { // JSON 역직렬화 인터페이스
    fun fromJSON(jsonText: String): T
}

class UseFactoryPerson(val firstName: String, val lastName: String) {
    companion object  { // companion object도 인터페이스 구현 가능
        // 비어있는 동반 객체 선언

    }
}

fun UseFactoryPerson.Companion.fromJSON(json: String) : UseFactoryPerson { // 확장 함수 선언
    TODO("Not yet implemented")
}

fun <T> loadFromJSON(factory: JSONFactory<T>): T { //JSON으로부터 각 원소를 다시 만들어내는 추상 팩토리
    TODO("Not yet implemented")

}


fun main() {
    A.bar() // 자바의 static 메서드 호출이나 staic field 사용 구문과 같아짐

    val subscribingUser = UseFactoryUser.newSubscribingUser("rlawlsdk.1127@daum.net")
    val facebookUser = UseFactoryUser.newFacebookUser(2)
    print(subscribingUser.nickname)

//    loadFromJSON(UseFactoryPerson) 동반 객체의 인스턴스를 해당 팩토리 함수에 넘길 수 있다.
    val p = UseFactoryPerson.fromJSON("") // 동반 객체 안에서 fromJSON 함수를 정의한 것처럼 사용 가능678
}