package com.example.learnkotlin.kotlin_in_action.chapter8

data class Book(val title: String, val author: String)

fun titleStartsWithS(book: Book) = book.title.startsWith("S")
fun lengthOfTitleGraterThan5(book: Book) = book.title.length > 5
fun authorStartsWithB(book: Book) = book.author.startsWith("B")



inline infix fun <P> ((P) -> Boolean).and(crossinline predicate: (P) -> Boolean): (P) -> Boolean {
    return { p: P -> this(p) && predicate(p) }
}

fun main() {
    val book1 = Book("Start with why", "Simon Sinek")
    val book2 = Book("Sare to lead", "Brene Brown")
    val books = listOf(book1, book2)


    // 중간 리스트가 3번 생기므로 낭비
    val filteredBooks = books
        .filter(::titleStartsWithS)
        .filter(::authorStartsWithB)
        .filter(::lengthOfTitleGraterThan5)

    println(filteredBooks)


    val filteredBooks2 = books.filter(
        ::titleStartsWithS
                and ::authorStartsWithB
                and ::lengthOfTitleGraterThan5
    )

    println(filteredBooks2)
}
