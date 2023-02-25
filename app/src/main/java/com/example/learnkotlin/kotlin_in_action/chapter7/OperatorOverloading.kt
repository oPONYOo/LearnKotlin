package com.example.learnkotlin.kotlin_in_action.chapter7

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class Point(val x: Int, val y: Int) { // 불변 클래스
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
}


operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun Point.times(scale: Double): Point {
    return Point((x * scale).toInt(), (y * scale).toInt())
}

// 단항 연산자 오버로딩
operator fun Point.unaryMinus(): Point {
    return Point(-x, -y)
}

operator fun Point.get(index: Int): Int { // get 연산자 함수 정의
    return when (index) {
        0 -> x
        1 -> y
        else -> throw java.lang.IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.set(index: Int, value: Int) { // set 연산자 함수 정의
    when (index) {
        0 -> x = value
        1 -> y = value
        else -> throw java.lang.IndexOutOfBoundsException("Invalid coordinate $index")
    }

}

class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }

}

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

// in 연산자와 대응하는 함수는 contains
operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y
}


operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    object : Iterator<LocalDate> {

        var current = start

        override fun hasNext() =
            current <= endInclusive

        override fun next(): LocalDate = current.apply {
            current = plusDays(1)
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2)
    // Point var2 = p1.plus(p2);
    // + 연산자는 plus 함수로 컴파일된다.

    val p = Point(10, 20)
    println(p * 1.5)

    var point2 = Point(1, 2)
    point2 += Point(3, 4)
    // point2 = point2.plus(new Point(3, 4));
    // += 는 새로운 Point 객체를 가르킨다.(+= 연산은 참조를 다른 참조로 바꿔치기함.)
    // +=, -= 등의 복합 대입 연산자도 지원한다.
    println(point2)


    // 객체의 내부 상태를 변경하고 싶을 때
    val nums = ArrayList<Int>()
    nums += 42 // var5.add(var6);
//    nums.plusAssign(42)
/*@kotlin.internal.InlineOnly
public inline operator fun <T> MutableCollection<in T>.plusAssign(element: T) {
    this.add(element)
}*/
    println(nums[0])

    val list = arrayListOf(1, 2)
    list += 3 // += 메모리에 있는 객체 상태 변화
    println(list)
    val newList = list + listOf(4, 5) // + 새로운 컬렉션 반환
    println(newList)

    val minusP = Point(10, 20)
    println(-minusP)
    println(p[1]) // y좌표 읽어오기(get)
    // []를 사용하여 접근하면 get함수 호출로 컴파일됨
    // int var17 = get(p, 1);


    val mutableP = MutablePoint(10, 20)
    mutableP[1] = 42
    // []를 사용한 대입문은 set함수 호출로 컴파일됨
    // set(mutableP, 1, 42);
    println(mutableP)


    val person1 = Person("Alice", "Smith")
    val person2 = Person("Bob", " Johnson")
    println(person1 < person2)
    println("abc" < "bac")

    val rect = Rectangle(Point(10, 20), Point(50, 50))
    // in 연산자는 contains 함수 호출로 컴파일
    println(Point(20, 30) in rect)
    // in의 우항은 contains 함수의 수신객체
    // in의 좌항은 contains 함수의 인자로 전달
    println(Point(5, 5) in rect)

    val now = LocalDate.now()
    // .. 연산자는 rangeTo 함수 호출로 컴파일
    // ClosedRange 인스턴스  반환
    val vacation = now..now.plusDays(20) // 오늘..오늘+20
    // ClosedRange vacation = RangesKt.rangeTo((Comparable)now, (Comparable)now.plusDays(20L));
    // Comparable 인터페이스를 구현한 객체에 대해 적용할 수 있는 rangeTo 함수
    println(now.plusWeeks(1) in vacation)

    // rangeTo 연산자는 다른 산술연산자보다 우선순위가 낮다.
    // 명확한 표현을 위해 괄호로 감싸주기
    val n = 10
    println(0..(n + 1))

    // range의 메서드를 호출하려면 괄호로 감싸주기
    (0..n).forEach { print(it) }


    val newYear = LocalDate.ofYearDay(2017, 1)
    val daysOff = newYear.minusDays(1)..newYear
    // ClosedRange<LocalDate>에 대한 확장함수 iterator를 정의했기 때문에 LocalDate의 범위 객체를 for루프에 사용할 수 있다.
    for (dayOff in daysOff) println(dayOff)

}