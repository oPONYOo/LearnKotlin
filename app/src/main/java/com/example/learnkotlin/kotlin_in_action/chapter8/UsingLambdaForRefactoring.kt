package com.example.learnkotlin.kotlin_in_action.chapter8

// 함수타입과 람다 식은 재활용하기 좋은 코드를 만들 때 활용할 수 있다
// 람다를 사용해서 코드의 중복을 없애기
data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

fun main() {
    val log = listOf(
        SiteVisit("/", 34.0, OS.WINDOWS),
        SiteVisit("/", 22.0, OS.MAC),
        SiteVisit("/login", 12.0, OS.WINDOWS),
        SiteVisit("/signUp", 8.0, OS.IOS),
        SiteVisit("/", 16.3, OS.ANDROID)
    )

    // 윈도우일때만 평균 구하기 가능
    val averageWindowsDuration = log
        .filter { it.os == OS.WINDOWS }
        .map(SiteVisit::duration)
        .average()

    println(averageWindowsDuration)


    // 윈도우 종류에 따라 평균구하기 가능
    fun List<SiteVisit>.averageDurationFor(os: OS) =
        filter { it.os == os }.map(SiteVisit::duration).average()

    println(log.averageDurationFor(OS.WINDOWS))
    println(log.averageDurationFor(OS.MAC))


    // 모바일 일때 평균
    val averageMobileDuration = log.filter {
        it.os in setOf(OS.IOS, OS.ANDROID)
    }
        .map(SiteVisit::duration)
        .average()

    println(averageMobileDuration)


    // 복잡한 조건이 있을 경우 함수타입을 사용하면 필요한 조건을 파라미터로 뽑아낼 수 있다.
    fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
        filter(predicate).map(SiteVisit::duration).average()

    println(log.averageDurationFor { it.os in setOf(OS.ANDROID, OS.IOS) })
    println(log.averageDurationFor { it.os == OS.IOS && it.path == "/signUp" })


}