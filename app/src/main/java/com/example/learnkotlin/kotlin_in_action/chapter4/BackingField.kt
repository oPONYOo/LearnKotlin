package com.example.learnkotlin.kotlin_in_action.chapter4

class BackingFieldUser(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println(
                """
            Address was changed for $name:
            $field -> "$value".""".trimIndent() // backing field 값 읽기
            )
            field = value //backing field 값 변경
        }


}

class LengthCounter {
    var counter: Int = 0
    private set  // 세터의 가시성을 private으로 지정했기 떄문에 이 클래스 밖에서 프로퍼티의 값을 바꿀 수 없다.

    fun addWord(word: String) {
        counter += word.length
    }
}

fun main() {
    val user = BackingFieldUser("JINA")
    user.address = "SEOUL"

    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Ho!")
    println(lengthCounter.counter)

}