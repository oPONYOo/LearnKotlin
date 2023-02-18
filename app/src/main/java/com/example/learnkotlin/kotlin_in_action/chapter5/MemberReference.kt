package com.example.learnkotlin.kotlin_in_action.chapter5


// val getAge = {person: Person -> person.age }

//참조 대상이 함수인지 프로퍼티인지와는 관계 없이 멤버 참조 뒤에 괄호를 넣으면 안된다.
val getAge = Person::age // 클래스::멤버

fun salute() = println("salute!") // 최상위 함수

fun sendEmail(person: Person, message: String) {}

val action = {person: Person, message: String -> sendEmail(person, message) }
val nextAction = ::sendEmail


// constructor reference(생성자 참조)
val createPerson = ::Person
val p = createPerson("JINA", 24)





fun main() {
    println(getAge(Person("jina", 24)))
    run(::salute) // 최상위 함수 참조. 최상위 함수이므로 ::앞에 클래스 이름을 생략한다.

    println(p)

    // 확장 함수 참조도 가능
    fun Person.isAdult() = age >= 20
    val predicate = Person::isAdult


    // bound member reference
    val jinaAgeFunction = p::isAdult
    /*
    KFunction jinaAgeFunction = new Function0(p) {
         // $FF: synthetic method
         // $FF: bridge method
         public Object invoke() {
            return this.invoke();
         }

         public final boolean invoke() {
            return MemberReferenceKt.isAdult((Person)this.receiver);
         }
      };
    }*/
    println(jinaAgeFunction()) // 인자가 없는 함수. 호출 시 수신 대상 객체를 지정해줄 필요없음
//    boolean var1 = (Boolean)((Function0)jinaAgeFunction).invoke();


    println(predicate(p)) // 인자가 하나. 인자로 받은 사람의 나이를 반환
//    var1 = (Boolean)((Function1)predicate).invoke(p);
}