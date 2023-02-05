package com.example.learnkotlin.smartcast

interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr // left, right에는 Num 혹은 Sum이 인자로 올 수 있다.

private fun eval(e: Expr): Int =
    when (e) { //타입 검사 -> 스마트 캐스트가 이뤄지기 때문에 강제로 캐스팅할 필요가 없다.
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }

// 분기에 로직이 들어가야 한다면 블록으로 감싸주고 return 해줄 값(결과값)을 가장 마지막 줄에 작성한다.
private fun evalWithLogging(e: Expr): Int =
    when (e) { //타입 검사 -> 스마트 캐스트가 이뤄지기 때문에 강제로 캐스팅할 필요가 없다.
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun main() {
    println(eval(Sum(Num(1), Num(2))))
    println(evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4))))
}