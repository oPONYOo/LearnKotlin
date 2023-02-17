package com.example.learnkotlin.kotlin_in_action.chapter5


// val getAge = {person: Person -> person.age }

//참조 대상이 함수인지 프로퍼티인지와는 관계 없이 멤버 참조 뒤에 괄호를 넣으면 안된다.
val getAge = Person::age // 클래스::멤버

fun salute() = println("salute!") // 최상위 함수

fun sendEmail(person: Person, message: String) {}

val action = {person: Person, message: String -> sendEmail(person, message) }
val nextAction = ::sendEmail


// constructor reference(생성자 참)
val createPerson = ::Person
val p = createPerson("JINA", 24)

// 확장 함수 참조도 가능
fun Person.isAdult() = age >= 20
val predicate = Person::isAdult


/*public static final boolean isAdult(@NotNull Person $this$isAdult) {
    Intrinsics.checkNotNullParameter($this$isAdult, "$this$isAdult");
    return $this$isAdult.getAge() >= 20;

     @NotNull
   public static final KFunction getPredicate() {
      return predicate;
   }
}*/
fun main() {
    println(getAge(Person("jina", 24)))
    run(::salute) // 최상위 함수 참조. 최상위 함수이므로 ::앞에 클래스 이름을 생략한다.

    println(p)

    // bound member reference
    val jinaAgeFunction = p::isAdult
    /*val jinaAgeFunction: KFunction = object : Function0(MemberReferenceKt.p) {
        // $FF: synthetic method
        // $FF: bridge method
        operator fun invoke(): Any? {
            return this.invoke()
        }

        // 인스턴스에 대해 멤버를 호출
        operator fun invoke(): Boolean {
            return MemberReferenceKt.isAdult(this.receiver as Person?)
        }
    }*/
    println(jinaAgeFunction())
    println(predicate(p))
}