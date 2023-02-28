package com.example.learnkotlin.kotlin_in_action.chapter7

class Email {

}

// backing property 기법
// thread - safe 하지않음
class BackingPropertyPerson(val name: String) {
    private var _emails: List<Email>? = null // emails의 위임 객체 역할. 데이터를 저장한다.
    val emails: List<Email> // _emails라는 프로퍼티에 대한 읽기 연산 제공
    get() {
        if (_emails == null) _emails = loadEmails(this) // 최조 접근 시 이메일을 가져온다.
        return _emails!! // 저장해둔 데이터가 있으면 해당 데이터 반환
    }

}

fun loadEmails(person: BackingPropertyPerson): List<Email> {
    println("Load emails for ${person.name}")
    return listOf()
}

// 위임 프로퍼티는 데이터를 저장할 때 쓰이는 backing property와
// 값이 오직 한 번만 초기화됨을 보장하는 게터 로직을 함께 캡슐화 해준다.
class ByLazyPerson(val name: String) {
    val emails by lazy { loadEmails(this) }
    // lazy 함수의 인자는 값을 초기화할 때 호출할 람다

    /*@NotNull
    public final List getEmails() {
        Lazy var1 = this.emails$delegate;
        Object var3 = null;
        return (List)var1.getValue();
    }*/
    // lazy 함수는 코틀린 관례에 맞는 시그니처의 getValue 메서드가 들어있는 객체를 반환한다.


    /*this.emails$delegate = LazyKt.lazy((Function0)(new Function0() {
        // $FF: synthetic method
        // $FF: bridge method
        public Object invoke() {
            return this.invoke();
        }

        @NotNull
        public final List invoke() {
            return DelegatedPropertyEx1Kt.loadEmails(ByLazyPerson.this);
        }
    }));*/
}

//public actual fun <T> lazy(initializer: () -> T): Lazy<T> = SynchronizedLazyImpl(initializer)


fun loadEmails(person: ByLazyPerson): List<Email> {
    println("Load emails for ${person.name}")
    return listOf()
}

fun main() {

    val p = BackingPropertyPerson("Jina")
    p.emails // 최초로 emails를 읽을 때 단 한번만 이메일을 가져온다.
    p.emails

    val p2 = ByLazyPerson("ponyo")
    p2.emails
    p2.emails
}