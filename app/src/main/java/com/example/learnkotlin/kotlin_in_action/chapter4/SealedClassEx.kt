package com.example.learnkotlin.kotlin_in_action.chapter4



// 상위 클래스에 sealed 변경자를 붙이면 그 상위 클래스를 상속한 하위 클래스 정의를 제한할 수 있다.
sealed class Expr { // private한 생성자를 가진다. -> 클래스 내부에서만 호출 가능
    class Num(val value: Int) : Expr()   // Expr의 하위 클래스로 상위클래스 안에 중첩시켜서 선언
    class Sum(val left: Expr, val right: Expr) : Expr()
}
// 봉인된 클래스는 외부에 자신을 상속한 클래스를 둘 수 없다.

private fun eval(e: Expr): Int =
    when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
    }

fun main() {
    println(eval(Expr.Sum(Expr.Num(1), Expr.Num(2))))
}