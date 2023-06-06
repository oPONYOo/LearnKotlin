package com.example.learnkotlin

fun main() {
    val greeting = ""
    println(greeting.ifEmpty { returnGreetingString() })
    // 비어있지 않으면 이 문자 시퀀스를 반환하고, 문자 시퀀스가 비어있으면 default 함수를 호출한 결과를 반환한다.
    /*@SinceKotlin("1.3")
    @kotlin.internal.InlineOnly
    public inline fun <C, R> C.ifEmpty(defaultValue: () -> R): R where C : CharSequence, C : R =
        if (isEmpty()) defaultValue() else this*/
    println(greeting.isEmpty())
}

private fun returnGreetingString() = "bye"