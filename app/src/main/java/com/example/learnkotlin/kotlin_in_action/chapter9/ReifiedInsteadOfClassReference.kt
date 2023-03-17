package com.example.learnkotlin.kotlin_in_action.chapter9

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import java.util.ServiceLoader


inline fun <reified T> loadService(): ServiceLoader<T> {
    return ServiceLoader.load(T::class.java) // T::class로 타입 파라미터의 클래스를 가져온다.
}

inline fun <reified T : Activity>
        Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent) // Context내부 함수
}


fun main() {
    //val serviceImpl = ServiceLoader.load(Service::class.java)
// 코틀린 클래스에 대응하는 java.lang.Class를 참조를 얻는 방법 == Service.Class

    // 읽어들일 서비스 클래스를 loadService 함수의 타입 인자로 지정한다.
    val serviceImpl = loadService<Service>()





}