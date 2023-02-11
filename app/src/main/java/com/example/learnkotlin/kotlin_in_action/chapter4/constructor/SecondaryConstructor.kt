package com.example.learnkotlin.kotlin_in_action.chapter4.constructor

import android.content.Context
import android.util.AttributeSet
import android.view.View


open class SecondaryView { //primary 생선자 선언X(괄호가 없음)
    constructor(ctx: Context) { // secondary 생성자

    }

    constructor(ctx: Context, attr: AttributeSet) {

    }


}
// 클래스에 주 생성자가 없다면 모든 보조 생성자는 반드시 상위 클래스를 초기화하거나
// 다른 생성자에게 생성을 위임해야한다.
class MyButton : View {
    /*constructor(ctx: Context)
            : this(ctx, MY_STYLE) { // this()를 통해 클래스 자신의 다른 생성자를 호출 할 수 있다.
    }*/

    constructor(ctx: Context, attr: AttributeSet)
            : super(ctx, attr) {
// 부 생성자가 상위 클래스 생성자 호출
// MyButton의 부생성자가 View 상위 클래스 생성자에게 객체 생성을 위임

    }


}