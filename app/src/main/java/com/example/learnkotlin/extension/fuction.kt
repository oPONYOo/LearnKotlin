package com.example.learnkotlin.extension


//fun String.lastCHar(): Char = this.get(length - 1)

//fun String.lastCHar(): Char = this[length - 1]

fun String.lastCHar(): Char = get(length - 1)

fun View.showOf() = println("I'm a view")
fun Button.showOff() = println("I'm a button")

open class View {
    open fun click() = println("View Clicked")
}

open class Button: View() {
    override fun click() = println("Button Clicked")
}

fun main() {
    println("JINA".lastCHar())
    val view: View = Button() // 정적 디스패치: 수신 객체로 지정한 변수의 정적 타입에 의해 어떤 확장함수가 호출될지 결정 -> 오버라이드X
    view.showOf()

    /*
    View view = new Button();
    //현재파일이름.showOff(view); 자바에서는 첫 번째 인자로 수신객체 전달
    */

}