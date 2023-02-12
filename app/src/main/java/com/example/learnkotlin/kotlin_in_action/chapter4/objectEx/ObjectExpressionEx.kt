package com.example.learnkotlin.kotlin_in_action.chapter4.objectEx

import android.view.View


/*
val listener = object : MouseAdapter() { // MouseAdapter를 확장하는 무명 객체 선언

    override fun mouseClicked(e: MouseEvent) {} // MouseAdapter의 메서드 오버라이드

    override fun mouseEntered(e: MouseEvent) {}
}*/


/*fun countClicks(window: Window) {
    var clickCount = 0 // 로컬 변수 정의

    window.addMouseListener(object : MouseAdapter()) {
        override fun mouseClicked(e: MouseEvent) {
            clickCount++  // 로컬 변수 값 변경
        }
    }
}*/


// 이름이 필요한 경우 아래처럼 변수에 무명 객체를 대입하면 된다.
// 메서드가 하나인 인터페이스를 구현할 땐 SAM 변환 사용
val listener = View.OnClickListener { }


