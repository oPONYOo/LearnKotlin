package com.example.learnkotlin.kotlin_in_action.chapter4


interface State: java.io.Serializable

interface View: State {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

// 자바
/*public class Button implements View {
    @Override
    public State getCurrentState() {
        return new ButtonState();
    }

    @Override
    public void restoreState(State: state) {}

    public class ButtonState implements State {} // static로 선언하면 바깥쪽 클래스에 대한 묵시적 참조가 사라진다.
}*/


// 코틀린
/*
class Button : View {

    override fun getCurrentState(): State = ButtonState()
    override fun restoreState(state: State) {
//        super.restoreState(state)
    }

    class ButtonState: State {
    } //자바의 static 중첩 클래스와 대응한다.

}
*/


