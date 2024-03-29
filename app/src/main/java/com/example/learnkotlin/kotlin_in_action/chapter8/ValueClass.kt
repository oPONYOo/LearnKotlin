package com.example.learnkotlin.kotlin_in_action.chapter8


// SecretPerson 클래스와 같은 래퍼 클래스를 사용하게 되면 함수를 호출할 때마다 객체를 생성하여 호출해야하는 비용 발생

fun sendSecretMessage(who: SecretPerson) = println("${who.nickName}님이 보낸 메세지를 확인해보세요.")


// value class는 바이트코드로 컴파일하는 과정에서 객체를 제거하고 value class의 프로퍼티로 대체하여 최적화
// value class는 반드시 val 프로퍼티만 허용
// data class와 다르게 toString(), hashCode(), equals() 메소드만 자동 생성
// value class는 == 연산만 지원하고 === 연산은 지원하지 않음
@JvmInline
value class SecretPerson private constructor(val nickName: String) {
    companion object {
        fun setRandomNickName(realName: String) = SecretPerson("${realName.random()}")
        fun setNickName(nickName: String) = SecretPerson(nickName)
    }

}


/*public final class ValueClassKt {
// 매개변수로 value class (SecretPerson)가 있으면 바이트코드로 변환할 때
//매개변수로 SecretPerson 객체의 프로퍼티를 가진 함수로 변환
// 함수 이름 뒤에 맹글링   ->  맹글링을 통해 함수가 겹치는 일이 없도록함                                                                                    // 중요!! 객체제거 //
    public static final void sendSecretMessage_TkzC85A*//* $FF was: sendSecretMessage-TkzC85A*//*(@NotNull String who) { // SecretPerson를 String 타입으로 변경한 함수로 반환
        Intrinsics.checkNotNullParameter(who, "who");
        String var1 = who + "님이 보낸 메세지를 확인해보세요.";
        System.out.println(var1);
    }

    public static final void main() {
        sendSecretMessage-TkzC85A(SecretPerson.Companion.setRandomNickName-Ea7wWAM("jina"));
        sendSecretMessage-TkzC85A(SecretPerson.Companion.setNickName-Ea7wWAM("ponyo"));
    }

    // $FF: synthetic method
    public static void main(String[] var0) {
        main();
    }
}*/

fun main() {
    sendSecretMessage(SecretPerson.setRandomNickName("jina"))
    sendSecretMessage(SecretPerson.setNickName("ponyo"))
}