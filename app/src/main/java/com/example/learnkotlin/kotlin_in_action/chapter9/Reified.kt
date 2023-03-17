package com.example.learnkotlin.kotlin_in_action.chapter9

// 코틀린의 타입소거로 인해 실행 시점에 제네릭 타입의 타입 인자 정보를 알 수 없다.
// 따라서 제네릭 클래스의 인스턴스가 있어도 그 인스턴스를 만들 때 사용한 타입 인자를 알아낼 수 없다.
// 제네릭 함수의 타입 인자도 마찬가지로 제네릭 함수가 호출되도 그 함수의 본문에서는 호출 시 쓰인 타입 인자를 알 수 없다.
//fun <T> isA(value: Any) = value is T
// error: Cannot check for instance of erased type: T


// reified 키워드: 이 타입 파라미터가 실행 시점에 지워지지 않음을 표시
// 컴파일러는 인라인 함수의 본문을 구현한 바이트코드를 그 함수가 호출되는 모든 지점에 삽입
inline fun <reified T> isA(value: Any) = value is T
/*public static final boolean isA(Object value) {
    int $i$f$isA = 0;
    Intrinsics.checkNotNullParameter(value, "value"); // Intrinsics 내장 함수
    Intrinsics.reifiedOperationMarker(3, "T");
    return value instanceof Object;
}*/



val items = listOf("once", 2, "three")


fun main() {
    println(isA<String>("abc"))
    /*var7 = value$iv instanceof String; // 컴파일러는 실체화한 타입 인자를 사용해 인라인 함수를 호출하는 각 부분의 정확한 인자를 알 수 있음
    System.out.println(var7);1*/
    println(isA<String>(123))



    // 인자로 받은 컬렉션의 원소 중에서 타입 인자로 지정한 클래스의 인스턴스만을 모아서 만든 리스트를 반환한다.
    // 타입 인자로 String을 지정함으로써 문자열만 필요하다는 사실을 기술한다.
    /**
     * Returns a list containing all elements that are instances of specified type parameter R.
     *
     * @sample samples.collections.Collections.Filtering.filterIsInstance
     */
    // reified 타입 파라미터인 R을 선언한 Iterable 객체를 확장한 R타입의 리스트를 반환하는 함수
    /*public inline fun <reified R> Iterable<*>.filterIsInstance(): List<@kotlin.internal.NoInfer R> {
        return filterIsInstanceTo(ArrayList<R>())
    }*/

    println(items.filterIsInstance<String>())
    // 타입 인자를 실행 시점에 알 수 있다.





    /*Iterable $this$filterIsInstance$iv = (Iterable)items;
    $i$f$filterIsInstance = false;
    Collection destination$iv$iv = (Collection)(new ArrayList());
    int $i$f$filterIsInstanceTo = false;
    Iterator var5 = $this$filterIsInstance$iv.iterator();

    while(var5.hasNext()) {
        Object element$iv$iv = var5.next();
        // 타입 인자로 쓰인 구체적인 클래스를 참조하는 바이트코드를 생성해 삽입.
        if (element$iv$iv instanceof String) { // 각 원소가 타입 인자로 지정한 클래스의 인스턴스인지 검사할 수 있다.

            destination$iv$iv.add(element$iv$iv);
        }
    }

    List var10 = (List)destination$iv$iv;
    System.out.println(var10);*/
}