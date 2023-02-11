package com.example.learnkotlin.kotlin_in_action.chapter4

open class RichButton: Clickable { // 다른 클래스가 이 클래스를 상속할 수 있다.

    fun disalbe() {} //코틀린에서 기본이 final이므로 하위 클래스가 이 메서드를 오버라이드할 수 없다.

    open fun animate() {} // open을 붙혀주면 하위 클래스에서 이 메서드를 오버라이드를 할 수 있다.

    final override fun click() {}
    // final이 없는 override 메서드나 프로퍼티는 기본적으로 open이다
    // 오버라이드하는 메서드의 구현을 하위 클래스에에서 오버라이드하지 못하게 금지하려면
    // 오버라이드하는 메서드 앞에 final을 명시해야한다.
}