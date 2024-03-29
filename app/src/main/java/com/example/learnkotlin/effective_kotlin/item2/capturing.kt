package com.example.learnkotlin.effective_kotlin.item2

private fun example1() {
    var numbers = (2..100).toList()
    val primes = mutableListOf<Int>()
    while (numbers.isNotEmpty()) {
        val prime = numbers.first()
        primes.add(prime)
        numbers = numbers.filter { it % prime != 0 }
    }
    println(primes)
}


private fun example2() {
    val primes: Sequence<Int> = sequence {
        var numbers = generateSequence(2) { it + 1 }

        while (true) {
            val prime = numbers.first() // 안바뀜
            yield(prime)
            numbers = numbers.drop(1)
                .filter { it % prime != 0 }
        }
    }
    println(primes.take(10).toList())
}

private fun wrongExample2() {
    val primes: Sequence<Int> = sequence {
        var numbers = generateSequence(2) { it + 1 }
        var prime: Int // 변수 캡쳐
        //
        while (true) {
            prime = numbers.first()
            yield(prime)
            numbers = numbers.drop(1)
                .filter { it % prime != 0
                //
                } // 시퀀스를 사용해서 필터링 지연
            // 지연 수행
            // 캡쳐된 시점과 값이 달라져셔
        }
    }
    println(primes.take(10).toList())
}
/*act1)
numbers.first()
-> 결과 2

act2)
numbers.drop(1).filter { it % 2 != 0 }.first()
-> 2 drop -> 3 O => 결과 3

act3)
numbers.drop(1).filter { it % 3 != 0 }
.drop(1).filter { it % 3 != 0 }
.first()
-> 2가 drop -> 3 x -> 4 O
-> 4가 drop ->  5 O
-> 결과 5
act4)
numbers.drop(1).filter { it % 5 != 0 }
.drop(1).filter { it %  5!= 0 }
.first()*/

fun main() {
    example1()
    example2()
    wrongExample2()
}


