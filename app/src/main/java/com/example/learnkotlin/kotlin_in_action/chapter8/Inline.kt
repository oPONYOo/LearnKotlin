package com.example.learnkotlin.kotlin_in_action.chapter8

import com.example.learnkotlin.kotlin_in_action.chapter5.action
import java.util.concurrent.locks.Lock

// inline: 고차 함수를 사용하면 런타임 패널티가 있기 때문에 함수 구현 자체를 코드에 넣음으로써 오버헤드를 없앨 수 있다.
// inline으로 선언하면 그 함수의 본문이 inline된다.
// 함수를 호출하는 코드를 바이트코드로 컴파일하는 대신 함수 본문을 번역하는 바이트코드로 컴파일한다.
inline fun <T> synchronized(lock: Lock, action: () -> T ): T {
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }
}

fun <T> noInlinesynchronized(lock: Lock, action: () -> T ): T {
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }
}

fun foo(l: Lock) {
    println("Before sync")
    synchronized(l) {
        println("Action")
    }
    noInlinesynchronized(l) {
        println("noInline Action")
    }

    println("After sync")
}



// foo 컴파일한 코드
// synchronized 함수의 본문뿐 아니라 synchronized에 전달된 람다의 본문도 함께 인라이닝된다는 점을 유의
// inline을 사용하면 람다의 본문에 의해 만들어지는 바이트코드는 무명클래스로 감싸지 않음
/*public static final void foo(@NotNull Lock l) {
    Intrinsics.checkNotNullParameter(l, "l");
    String var1 = "Before sync";
    System.out.println(var1);
    int $i$f$synchronized = false;
    l.lock(); //  synchronized 함수 인라이닝
!!inline으로 선언하면 그 함수의 본문이 inline
    try {
        int var2 = false;
        String var3 = "Action";
        System.out.println(var3); // 람다 코드의 본문 인라이닝
        Unit var7 = Unit.INSTANCE;
    } finally {
        l.unlock();
    }


!!여기서 내부적으로 객체 생성과 함께 함수 호출을 하게 되어 있어서, 이런 부분에서 오버헤드가 생길 수 있다. inline 키워드는 이런 오버헤드를 없애기 위해 사용한다.
    noInlinesynchronized(l, (Function0)null.INSTANCE);
    var1 = "After sync";
    System.out.println(var1);
}*/


// inline함수 호출하면서 람다를 넘기는 대신 함수 타입의 변수를 넘길 수 있음
class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        synchronized(lock, body)
    }
}


/*public final class LockOwner {
    @NotNull
    private final Lock lock;

    public final void runUnderLock(@NotNull Function0 body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Lock lock$iv = this.lock;
        int $i$f$synchronized = false;
        lock$iv.lock();

        try {
            Object var4 = body.invoke(); // synchronized를 호출하는 부분에서 람다를 알 수 없으므로 본문(body())는 인라이닝되지 않는다.
        } finally {
            lock$iv.unlock();
        }

    }

    @NotNull
    public final Lock getLock() {
        return this.lock;
    }

    public LockOwner(@NotNull Lock lock) {
        Intrinsics.checkNotNullParameter(lock, "lock");
        super();
        this.lock = lock;
    }
}*/

fun main() {
    /*val l = Lock()
    synchronized(l) {

    }*/



}