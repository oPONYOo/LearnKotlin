package com.example.learnkotlin.kotlin_in_action.chapter8

fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}
// 컴파일
// 함수타입은 일반 인터페이스로 바뀐다. 함수 타입의 변수는 FunctionN 인터페이스를 구현하는 객체를 저장한다.
// 함수 인자의 개수에 따라 Function0, Function1 등의 인터페이스 제공 22개까지 있음
// 각 인터페이스에는 invoke 메서드 정의가 들어있고 invoke를 호출하면 함수를 실행할 수 있다.
/*public static final void processTheAnswer(@NotNull Function1 f) {
    Intrinsics.checkNotNullParameter(f, "f");
    int var1 = ((Number)f.invoke(42)).intValue(); // 힘수타입 변수는 FunctionN 인터페이스를 구현하는 클래스의 인스턴스를 저장, invoke 메소드 본문에는 람다의 본문이 들어간다.
    System.out.println(var1);
}*/

fun main() {
    // null이 될 수 있는 반환 타입
    var canReturnNull: (Int, Int) -> Int? = { x, y -> null }

    // null이 될 수 있는 함수 타입 변수
    // 함수 타입 전체가 null이 될 수 있는 타입임을 선언하기 위해 함수 타입을 괄호로 감싸고 그 뒤에 물음표를 붙여야만 한다.
    var funOrNull: ((Int, Int) -> Int)? = null

    fun performRequest( // 파라미터에 이름을 지정할 수 있다.
        url: String,
        callback: (code: Int, content: String) -> Unit
    ) {

    }

    val url = "http://kotl.in"
    performRequest(url) { code, content -> } // 함수에서 이름 붙힌 이름을 사용할 수 있다.
    performRequest(url) { code, page -> } // 원하는 다른 이름을 지정할 수도 있다.


    fun twoAndThree(operation: (Int, Int) -> Int) {
        val result = operation(2, 3)
        println("The result is $result")
    }

    twoAndThree { a, b -> a + b }
    twoAndThree { a, b -> a * b }

    fun String.filter(predicate: (Char) -> Boolean): String {
        val sb = StringBuilder()
        for (idx in 0 until length) {
            val element = get(idx)
            if (predicate(element)) sb.append(element) // predicate 파라미터로 전달받은 함수 호출
        }
        return sb.toString()
    }

    println("ab1c".filter { it in 'a'..'z' }) // 람다를 predicate 파라미터로 전달 in -> contains


    // 함수 타입의 파라미터에 대한 디폴트 값 지정가능
    fun <T> Collection<T>.defaultType(transform: (T) -> String = { it.toString() }) {

    }

    listOf("JINA", "PONYO").defaultType()




}