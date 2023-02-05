package com.example.learnkotlin.range

private fun recogninze(c: Char) = when(c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z' -> "It's a letter!"
    else -> "I don't know"
}

fun main() {
    println(recogninze('8'))
    println(recogninze('x'))
    println("Kotlin" in "Java".."Scala")
    println("Kotlin" !in setOf("Java", "Scala"))
}