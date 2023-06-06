package com.example.learnkotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.TestOnly
import java.io.Closeable
import java.io.InputStreamReader
import java.net.Socket


// use Extensions: Closeable을 상속받는 모든 class에 접근할 수 있는 use 확장 함수로,
// try/catch/finally포함하고, 필요한 오류 정보는 외부로 보내준다.


// 사용예시
@TestOnly
fun test() = runBlocking {
    // exception은 노출 시키기 때문에 try/catch문으로 한 번 감싸줘야한다.
    try {
        withContext(Dispatchers.IO) {
            Socket("", 80).use { socket -> // 중첩으로 사용시 it말고 명확한 이름 사용
                socket.getInputStream().use { inputStream ->
                    InputStreamReader(inputStream).use { reader ->
                        // throw java.lang.Exception("Read error!!!!")
                        println(reader.readLines())
                    }
                }

            }
        }

    } catch (e: Exception) {

    }
}

/*

*/
/**
 * Executes the given [block] function on this resource and then closes it down correctly whether an exception
 * is thrown or not.
 *
 * @param block a function to process this [Closeable] resource.
 * @return the result of [block] function invoked on this resource.
 *//*

@InlineOnly
@RequireKotlin("1.2", versionKind = RequireKotlinVersionKind.COMPILER_VERSION, message = "Requires newer compiler version to be inlined correctly.")
public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    var exception: Throwable? = null
    try {
        return block(this)
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        when {
            apiVersionIsAtLeast(1, 1, 0) -> this.closeFinally(exception)
            this == null -> {}
            exception == null -> close()
            else ->
                try {
                    close()
                } catch (closeException: Throwable) {
                    // cause.addSuppressed(closeException) // ignored here
                }
        }
    }
}*/
