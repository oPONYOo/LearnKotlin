package com.example.learnkotlin.kotlin_in_action.chapter4

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!!")
    protected fun whisper() = println("Let's talk!")
}

/*fun TalkativeButton.giveSpeech() {// 에러 'public' member exposes its 'internal' receiver type TalkativeButton

    yell()  //에러 Cannot access 'yell': it is private in 'TalkativeButton'

    whisper() // 에러 Cannot access 'whisper': it is protected in 'TalkativeButton'
}*/
// public 함수인 giveSpeech 안에서 가시성이 낮은 타입인 TalkativeButton을 참조하지 못하게 한다.
// 클래스를 확장한 함수는 그 클래스의 private이나 protected 멤버에 접근할 수 없다.