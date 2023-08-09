package com.example.learnkotlin.thejoyofkotlin.chapter1

import junit.framework.Assert.assertEquals
import org.junit.Test

class CreditCard {
    fun charge(price: Int) = Unit
}

class Donut(
    val price: Int = 100
)
/*fun buyDonut(creditCard: CreditCard): Donut {
    val donut = Donut()
    creditCard.charge(donut.price) // 부수 효과(side effect) 테스트를 위해 mocking해야함 ->  테스트하기 어려움
    return donut
}*/

// buyDonut 함수가 Donut과 Payment를 한꺼번에 반환하도록 리팩토링
//class Payment(val creditCard: CreditCard, val amount: Int)

data class Purchase(val donut: Donut, val payment: Payment)


// creditCard를 생각할 필요가 없어졌댜!
fun buyDonut(creditCard: CreditCard): Purchase {
    val donut = Donut()
    val payment = Payment(creditCard, donut.price)
    return Purchase(donut, payment)
}

// 같은 신용카드로 여러 변 결제할 수 있는 combine 함수
class Payment(val creditCard: CreditCard, val amount: Int) {
    fun combine(payment: Payment): Payment =
        if (creditCard == payment.creditCard) Payment(creditCard, amount + payment.amount)
        else throw IllegalStateException("Card's don't match.") // 서로 다른 신용카드면 exception!

    companion object {
        // 신용카드 결제를 카드별로 묶는 함수
        fun groupByCard(payments: List<Payment>): List<Payment> =
            payments.groupBy { it.creditCard }
                .values
                .map { it.reduce(Payment::combine) }
    }
}

// combine 함수는 여러 도넛을 한 번에 사는 경우 비효율
class PurchaseList(val donuts: List<Donut>, val payment: Payment)

// 도넛 많이 가능
fun buyDonuts(quantity: Int = 1, creditCard: CreditCard): PurchaseList {
    val donut = Donut()
    return PurchaseList(List(quantity) { // 0부터 quantity - 1까지
        Donut()
    }, Payment(creditCard, donut.price * quantity))
}

class DonutShopKtTest {
    @Test
    fun testBuyDonuts() {
        val creditCard = CreditCard()
        val purchase = buyDonuts(5, creditCard)
        assertEquals(Donut().price * 5, purchase.payment.amount)
        assertEquals(creditCard, purchase.payment.creditCard)
    }
}
