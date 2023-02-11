package com.example.learnkotlin.kotlin_in_action.chapter2.expression.`when`

import java.lang.Exception

enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0), ORANGE(255, 165, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255),
    INDIGO(75, 0, 130), VIOLET(238, 130, 238); //메서드를 정의하는 경우 반드시 상수 목골과 메서드 정의 사이에 세미콜론을 넣어야 한다.

    fun rgb() = (r * 256 + g) * 256 + b
}

private fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

private fun getWarmth(color: Color) = when (color) { // Color을 import해서 이름만으로 사용가능
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}

private fun mix(c1: Color, c2: Color) = // Set 인스턴스 생성
    when (setOf(c1, c2)) { // Set은 순서를 보장하지 않음.
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("Dirty color")
    }

private fun mixOptimized(c1: Color, c2: Color) = //불필요한 객체 생성을 막을 수 있음. 하지만 가독성은 떨어짐
    when { //인자가 없으려면 각 분기의 조건이 Boolean 결과를 계산하는 식이어야 한다.
        (c1 == Color.RED && c2 == Color.YELLOW) ||
                (c1 == Color.YELLOW && c2 == Color.RED)
        -> Color.ORANGE
        (c1 == Color.YELLOW && c2 == Color.BLUE) ||
                (c1 == Color.BLUE && c2 == Color.YELLOW)
        -> Color.GREEN
        (c1 == Color.BLUE && c2 == Color.VIOLET) ||
                (c1 == Color.VIOLET && c2 == Color.BLUE)
        -> Color.ORANGE
        else -> throw Exception("Dirty color")
    }

private fun main() {
    println(Color.INDIGO.rgb())
    println(getMnemonic(Color.BLUE))
    println(getWarmth(Color.INDIGO))
    println(mix(Color.VIOLET, Color.BLUE))
    println(mixOptimized(Color.BLUE, Color.YELLOW))
}