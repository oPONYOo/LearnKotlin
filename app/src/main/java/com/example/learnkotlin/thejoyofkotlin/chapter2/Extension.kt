package com.example.learnkotlin.thejoyofkotlin.chapter2

val list: List<Int> = mutableListOf(1, 2, 3)
fun add() {
    val list2 = list + 1 // 원래 가변 리스트는 변하지 않고 그대로 남는다
    println(list2)
}
/*
*/
/**
 * Returns a list containing all elements of the original collection and then the given [element].
 *//*
public operator fun <T> Collection<T>.plus(element: T): List<T> {
    val result = ArrayList<T>(size + 1)
    result.addAll(this)
    result.add(element)
    return result
}*/


fun main() {
    add()
    println(list)
}