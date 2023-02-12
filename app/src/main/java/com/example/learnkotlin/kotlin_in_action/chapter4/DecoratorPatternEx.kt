package com.example.learnkotlin.kotlin_in_action.chapter4

import com.example.learnkotlin.kotlin_in_action.chapter3.infixcall.list

// 데코레이터 작성
class BeforeDelegatingCollection<T> : Collection<T> {
    private val innerList = arrayListOf<T>()
    override val size: Int
        get() = innerList.size

    override fun isEmpty(): Boolean = innerList.isEmpty()

    override fun iterator(): Iterator<T> = innerList.iterator()

    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)

    override fun contains(element: T): Boolean = innerList.contains(element)
}

class DelegatingCollection<T>(innerList: Collection<T> = ArrayList<T>()) :
    Collection<T> by innerList { } // 인터페이스를 구현할 때 by 키워드를 통해 인터페이스에 대한 구현을 다른 객체에 위임 중이라는 사실을 명시할 수 있다.


class CountingSet<T>(
    private val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet { // MutableCollection의 구현을 innerSet(내부 컨테이너 == 객체)에게 위임
    var objectsAdded = 0

    // 기존 클래스의 메서드에 위임하는 기본 구현으로 충분할 경우 오버라이드 할 필요없음

    override fun add(element: T): Boolean { // 동작을 변경하고 싶은 경우 위임하지 않고 오버라이드해서 새로운 구현 제공
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main() {
    val cset  = CountingSet<Int>()
    cset.addAll(listOf(1, 2, 3))
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")

}