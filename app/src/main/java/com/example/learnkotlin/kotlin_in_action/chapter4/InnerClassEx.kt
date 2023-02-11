package com.example.learnkotlin.kotlin_in_action.chapter4

class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer // 내부클래스에서 바깥쪽 클래스를 참조하기 this@outerclass이름
    }
}