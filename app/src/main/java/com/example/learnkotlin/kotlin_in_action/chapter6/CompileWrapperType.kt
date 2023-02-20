package com.example.learnkotlin.kotlin_in_action.chapter6

data class CompileWrapperType(val name: String, val age: Int? = null) {
    fun isOlderThan(other: CompileWrapperType) : Boolean? {
        if (age == null || other.age == null) return null
        return age > other.age // 컴파일러는 null 검사를 마친 다음에야 일반적인 값처럼 다루도록 허용한다.
    }
}

// 코틀린에서 null이 될 수 있는 primitive 타입을 사용하면 그 타입은 자바의 wrapper 타입으로 컴파일된다.
/*
public CompileWrapperType(@NotNull String name, @Nullable Integer age) {
    Intrinsics.checkNotNullParameter(name, "name");
    super();
    this.name = name;
    this.age = age;
}*/

fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0, 100)
    println("We're ${percent}% done!")
}

// 확장함수로 구현
/*public fun Int.coerceIn(minimumValue: Int, maximumValue: Int): Int {
    if (minimumValue > maximumValue) throw IllegalArgumentException("Cannot coerce value to an empty range: maximum $maximumValue is less than minimum $minimumValue.")
    if (this < minimumValue) return minimumValue
    if (this > maximumValue) return maximumValue
    return this
}*/


fun main() {
    showProgress(146)

    val listOfInts = listOf(1, 2, 3)
//    List listOfInts = CollectionsKt.listOf(new Integer[]{1, 2, 3});

    // 컴파일러가 필요한 변환을 자동으로 해준다.
    fun foo(l: Long) = println(l)
    val b: Byte = 1
    val l = b + 1L
    foo(42)
    /*byte b = 1;
    long l = (long)b + 1L;
    $fun$foo$1.invoke(42L);*/
}