package com.example.learnkotlin.kotlin_in_action.chapter4

interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!") // 디폴트 구현 가능
}

interface Focusable {
    fun setFocus(b: Boolean) =
        println("I ${if (b) "got" else "lost"} focus")

    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    override fun click() = println("I was clicked")
    override fun showOff() {
        // 상위 타입 구현을 호출할 때 super 사용
        // 이때 구체적인 타입을 지정하려면 super.<타입>
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

//    override fun showOff() = super<Clickable>.showOff()

}

fun main() {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}