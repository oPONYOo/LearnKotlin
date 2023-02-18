package com.example.learnkotlin.kotlin_in_action.chapter5

import android.content.Context
import android.widget.TextView



fun main() {
    // result 반복 사용
    /*fun alphabet(): String {
        val result = StringBuilder()
        for (letter in 'A'..'Z') {
            result.append(letter)
        }
        result.append("\nNow I know the alphabet")
        return result.toString()
    }*/

    // with을 활용하여 리팩토링
    fun alphabet(): String {
        val stringBuilder = StringBuilder()
//        with(stringBuilder, {}) 실제로 파라미터가 2개 있는 함수 1. 수신 객체 2. 람다
        return with(stringBuilder) { // 메서드를 호출하려는 수신 객체를 지정한다.
            for (letter in 'A'..'Z') {
                this.append(letter) // "this"를 명시해서 앞에서 지정한 수신 객체의 메서드를 호출한다.
            }
            append("\nNow I know the alphabet") // this 생략가능
            this.toString()
        }
    }

    // stringBuilder 변수 없도록 리팩토링
    // with가 반환하는 값은 람다 코드를 실행한 결과. 결과 == 람다 식의 본문에 있는 마지막 식의 값
    fun withAlphabet() = with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet")
        toString()
    }


    // apply는 수신객체를 반환한다.
    // apply는 확장 함수로 정의
    // 객체의 인스턴스를 만들면서 즉시 프로퍼티 중 일부를 초기화해야 하는 경우 유용
    fun applyAlphabet() = StringBuilder().apply {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet")
    }.toString()


    // buildString의 인자는 수신 객체 지정 람다이며 수신 객체는 항상 StringBuilder이다
    // buildString({})
    fun buildStringAlphabet() = buildString {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet")
    }

    println(alphabet())


    // apply 객체 초기화 활용 예시
    fun createViewWithCustomAttributes(context: Context) =
        TextView(context).apply {
            text = "Sample Text"
            textSize = 20.0f
            setPadding(10, 0, 0, 0)
        }

}