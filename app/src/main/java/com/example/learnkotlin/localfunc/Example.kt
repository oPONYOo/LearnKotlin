package com.example.learnkotlin.localfunc

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {

    // 방법 1.
    /*if (user.name.isEmpty()) {  // 필드 검증 중복
        throw IllegalArgumentException(
            "Can't save user ${user.id} : empty Name"
        )
    }

    if (user.address.isEmpty()) { // 필드 검증 중복
        throw IllegalArgumentException(
            "Can't save user ${user.id} : empty Address"
        )
    }*/


    // 방법 2. 로컬 함수 사용해서 코드 중복 줄이기
    /*fun validate(user: User, value: String, fieldName: String) {  // User 객체 전달 중복
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save iser ${user.id} : empty $fieldName"
            )
        }
    }
    // 로컬 함수 호출해서 각 필드를 검증한다.
    validate(user, user.name, "Name")
    validate(user, user.address, "Address")*/



    // 방법 3. saveUser함수의 user파라미터를 중복 사용하지 않는다.
    /*fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save iser ${user.id} : empty $fieldName"
            // 바깥 함수의 파라미터 user에 직접 접근할 수 있다.
            )
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")*/



    // 방법 4. 확장 함수 호출
    user.validateBeforeSave()


    // user 데이터베이스에 저장
}


// User 클래스 확장 함수
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save iser $id : empty $fieldName"
                // 바깥 함수의 파라미터 user에 직접 접근할 수 있다.
            )
        }
    }

    validate(name, "Name")
    validate(address, "Address")

}


fun main() {
    saveUser(User(1, "", "seoul"))
}