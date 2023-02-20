package com.example.learnkotlin.kotlin_in_action.chapter6

//fun f(): Unit {}
fun f() {}
/*public static final void f() {
}*/

interface Processor<T> {
    fun process(): T // process 함수가 어떤 값을 반환하라고 요구한다.
}

class NoResultProcessor: Processor<Unit> { // Unit도 Unit 값을 제공하기 떄문에 메서드에서 Unit 값을 반환하는데 아무 문제 없다.
    override fun process() {
        TODO("Not yet implemented")
    }
    /*public Object process() {
        this.process();
        return Unit.INSTANCE;
    }*/
    // NoResultProcessor에서 명시적으로 Unit을 반환하지 않아도 컴파일러가 묵시적으로 return Unit을 넣어준다.
}