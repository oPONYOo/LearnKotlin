package com.example.learnkotlin.kotlin_in_action.chapter4

abstract class Animated { // 추상클래스이므로 인스턴스화할 수 없다.
    abstract fun animate() // 추상함수로 하위클래스에서 반드시 오버라이드 해야한다.

    open fun stopAnimating() { // 비추상 함수는 기본적으로 final이지만 open으로 오버라이드를 허용할 수 있다.

    }

    fun animateTwice() { // final

    }
}