package com.example.learnkotlin.kotlin_in_action.chapter6



fun main() {
    val list = listOf<String>("Kotlin", "is")
    if(list is MutableList) {
        list.add("awesome") // 구현이 안되어있음.
    }
    // 쓰고 싶으면 toMutableList() 로 변환
    // list.toMutableList().add("")

}