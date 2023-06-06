package com.example.learnkotlin.kotlin_in_action


/*sealed class CommonErrors
object ServerError : CommonErrors()
object Forbidden : CommonErrors()
object Unauthorized : CommonErrors()

sealed class LoginErrors {
    data class InvalidUsername(val username: String) : LoginErrors()
    object InvalidPasswordFormat : LoginErrors()
    data class CommonError(val error: CommonErrors) : LoginErrors()
}

sealed class GetUserErrors {
    data class UserNotFound(val userId: String) : GetUserErrors()
    data class InvalidUserId(val userId: String) : GetUserErrors()
    data class CommonError(val error: CommonErrors) : GetUserErrors()


    fun handleError(loginError: LoginErrors): String = when (loginError) {
        is LoginErrors.InvalidUsername -> TODO()
        LoginErrors.InvalidPasswordFormat -> TODO()
        is LoginErrors.CommonError -> when (loginError.error) {
            Forbidden -> TODO()
            ServerError -> TODO()
            Unauthorized -> TODO()
        }
    }
}*/

// 디컴파일시 public abstract class로 변경
// @Metadata 어노테이션을 통해 컴파일러에게 클래스 계층 구조 정보를 제공
sealed class CommonErrors : LoginErrors, GetUserErrors {
    object ServerError : CommonErrors()
    object Forbidden : CommonErrors()
    object Unauthorized : CommonErrors()
}


// 디컴파일시 public interface로 변경
// @Metadata 어노테이션을 통해 컴파일러에게 클래스 계층 구조 정보를 제공
sealed interface LoginErrors {
    data class InvalidUsername(val username: String) : LoginErrors
    object InvalidPasswordFormat : LoginErrors
}

sealed interface GetUserErrors {
    data class UserNotFound(val userId: String) : GetUserErrors
    data class InvalidUserId(val userId: String) : GetUserErrors
}

fun handleLoginError(error: LoginErrors): String = when (error) {
    CommonErrors.Forbidden -> TODO()
    CommonErrors.ServerError -> TODO()
    CommonErrors.Unauthorized -> TODO()
    LoginErrors.InvalidPasswordFormat -> TODO()
    is LoginErrors.InvalidUsername -> TODO()
}

fun handleGetUserError(error: GetUserErrors): String = when (error) {
    CommonErrors.Forbidden -> TODO()
    CommonErrors.ServerError -> TODO()
    CommonErrors.Unauthorized -> TODO()
    is GetUserErrors.InvalidUserId -> TODO()
    is GetUserErrors.UserNotFound -> TODO()
}

fun handleCommonError(error: CommonErrors): String = when (error) {
    CommonErrors.Forbidden -> TODO()
    CommonErrors.ServerError -> TODO()
    CommonErrors.Unauthorized -> TODO()
}