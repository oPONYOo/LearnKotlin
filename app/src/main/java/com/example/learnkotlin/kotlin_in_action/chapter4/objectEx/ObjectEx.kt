package com.example.learnkotlin.kotlin_in_action.chapter4.objectEx

import java.io.File

object CaseInsensitiveFileComparator : Comparator<File> { //
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }

}

data class Person(val name: String) {
    object NameComparator : Comparator<Person> { // 클래스 안에 object 선언 가능(인스턴스는 단 한 개)
        override fun compare(p0: Person, p1: Person): Int = p0.name.compareTo(p1.name)
    }
}

fun main() {
    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))

    val files = listOf(File("/z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))

    val persons = listOf(Person("Ponyo"), Person("Ari"))
    println(persons.sortedWith(Person.NameComparator))

}