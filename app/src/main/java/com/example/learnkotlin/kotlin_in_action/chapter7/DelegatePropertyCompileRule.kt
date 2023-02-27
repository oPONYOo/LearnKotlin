package com.example.learnkotlin.kotlin_in_action.chapter7

/*
class C {
    var prop: Type by MyDelegate()
}*/

/*
class C {
    private val <delegate> = MyDelegate()
    var prop: Type
    get() = <delegate>.getValue(this, <property>)
    set(value: Type) = <delegate>.setValue(this, <property>, value)
}*/

// 컴파일러는 MyDelegate 클래스의 인스턴스를 감춰진 프로퍼티에 저장하며 감춰진 프로퍼티를 <delegate>라고 부른다.
// 컴파일러는 프로퍼티를 표현하기 위해 KProperty 타입의 객체를 사용한다. 이 객체를 <property>라고 부른다.
// 컴파일러는 모든 프로퍼티 접근자 안에 getValue, setValue 호출 코드를 생성해준다.
