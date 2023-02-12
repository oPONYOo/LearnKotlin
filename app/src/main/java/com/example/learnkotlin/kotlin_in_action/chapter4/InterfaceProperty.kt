package com.example.learnkotlin.kotlin_in_action.chapter4

interface User {
    val nickname: String
}

interface CustomUser {
    val email: String
    val nickname: String
        get() = email.substringBefore('@') // 필드가 없음(인터페이스는 상태를 저장할 수 없으므). 매번 결과를 계산
}

class PrivateUser(override val nickname: String): User // 주 생성자에 있는 프로퍼티

class SubscribingUser(val email: String): User {
    override val nickname: String
        get() = email.substringBefore('@') // 매번 호출될때 마다 계산하는 커스텀 게터 활용
}

fun getFaceBookName(id: Int) = "$id"

class FacebookUser(val accountId: Int): User {
    override val nickname = getFaceBookName(accountId)
// 프로퍼티 초기화 식. 객체 초기화 시 계산한 데이터를 backing field에 저장했다가 불러오는 방식
}