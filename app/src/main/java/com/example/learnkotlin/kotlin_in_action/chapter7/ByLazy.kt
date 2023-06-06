package com.example.learnkotlin.kotlin_in_action.chapter7


/*
// 람다를 받아서 Lazy 인스턴스 반환
// 최초 getter 실행은 lazy()에 넘겨진 람다를 실행, 결과를 기록한다.
// 최초가 아닌 getter 실행은 기록된 값을 반환한다.
// lazy는 프로퍼티의 값에 접근하는 최초 시점에 초기화 수행, 결과를 저장한 뒤 기록된 값을 재반환하는 인스턴스를 생성하는 함수
public actual fun <T> lazy(initializer: () -> T): Lazy<T> = SynchronizedLazyImpl(initializer)
//


public actual fun <T> lazy(mode: LazyThreadSafetyMode, initializer: () -> T): Lazy<T> =
    when (mode) {
        LazyThreadSafetyMode.SYNCHRONIZED -> SynchronizedLazyImpl(initializer)
        LazyThreadSafetyMode.PUBLICATION -> SafePublicationLazyImpl(initializer)
        LazyThreadSafetyMode.NONE -> UnsafeLazyImpl(initializer)
    }
*/


//@Volatile을 붙이면 변수의 값이 메인 메모리에만 저장되며,
// 멀티 쓰레드 환경에서 메인 메모리의 값을 참조하므로 변수 값 불일치 문제를 해결할 수 있게된다.
// 다만 CPU캐시를 참조하는 것보다 메인메모리를 참조하는 것이 더 느리므로, 성능은 떨어질 수 밖에 없다.

/*private class SynchronizedLazyImpl<out T>(initializer: () -> T, lock: Any? = null) : Lazy<T>, Serializable {
    private var initializer: (() -> T)? = initializer // 전달 받은 람다 initializer를 프로퍼티에 저장
    @Volatile private var _value: Any? = UNINITIALIZED_VALUE  // _value 프로퍼티를 통해 값을 저장, 초기화하기 전이므로 UNINITIALIZED_VALUE 저장
    // final field is required to enable safe publication of constructed instance
    private val lock = lock ?: this

    override val value: T
        get() {
            val _v1 = _value
            if (_v1 !== UNINITIALIZED_VALUE) {// 다른 스레드에서 이미 초기화를 진행했다면 저장 된 값을 반환
                @Suppress("UNCHECKED_CAST")
                return _v1 as T
            }

            return synchronized(lock) { //  초기화 블록 실행
                val _v2 = _value
                if (_v2 !== UNINITIALIZED_VALUE) {
                    @Suppress("UNCHECKED_CAST") (_v2 as T)
                } else {
                    val typedValue = initializer!!()  // 초기화가 되어있지 않다면 람다식 실행하고
                    _value = typedValue // 반환값 _value에 저장
                    initializer = null // 초기화 끝났으면 필요없는 initializer null로 초기화화
                   typedValue
                }
            }
        }

    override fun isInitialized(): Boolean = _value !== UNINITIALIZED_VALUE

    override fun toString(): String = if (isInitialized()) value.toString() else "Lazy value not initialized yet."

    private fun writeReplace(): Any = InitializedLazyImpl(value)
}*/

