package com.example.learnkotlin.kotlin_in_action.chapter5

import android.view.View.OnClickListener


fun main() {
    val postpone = Postpone()

    // 하지만 capture한 변수가 없는 람다에 대해서는 인스턴스가 단 하나만 생긴다.
    // invokedynamic?
    postpone.postponeComputation(1000) { println(42) }
    // 컴파일러는 자동으로 람다를 Runnable 인스턴스로 변환해준다.
//    postpone.postponeComputation(1000, (Runnable)null.INSTANCE);



    // Runnable을 구현하는 무명 객체를 명시적으로 만들어서 사용할 수 있다.
    // 객체를 명시적으로 선언하는 경우 메서드를 호출할 때마다 새로운 객체가 생성된다.
    postpone.postponeComputation(1000, object : Runnable {
        override fun run() {
            println(42)
        }
    })
    /*postpone.postponeComputation(1000, (Runnable)(new Runnable() {
        public void run() {
            byte var1 = 42;
            System.out.println(var1);
        }
    }));*/




    val runnable = Runnable { println(42) }
    fun handleComputation() {
        postpone.postponeComputation(1000, runnable) // 모든 handleComputation에 같은 객체를 사용한다.
    }
    /*final Runnable runnable = (Runnable)null.INSTANCE;
      Function0 var10000 = new Function0() {
         // $FF: synthetic method
         // $FF: bridge method
         public Object invoke() {
            this.invoke();
            return Unit.INSTANCE;
         }

         public final void invoke() {
            postpone.postponeComputation(1000, runnable);
         }
      };*/


    // 람다 안에서 id변수를 capture한다. capture하면 매 호출마다 같은 인스턴스를 사용할 수 없다.
    // captureHandleComputation를 호출할 때마다 새로 Runnable 인스턴스를 만든다.
    fun captureHandleComputation(id: String) {
        postpone.postponeComputation(1000) { println(id) }
    }
    /* Function1 var4 = new Function1() {
         // $FF: synthetic method
         // $FF: bridge method
         public Object invoke(Object var1) {
             this.invoke((String)var1);
             return Unit.INSTANCE;
         }

         public final void invoke(@NotNull final String id) {
             Intrinsics.checkNotNullParameter(id, "id");
             postpone.postponeComputation(1000, (Runnable)(new Runnable() {
                 public final void run() {
                     String var1 = id; // 람다가 변수를 capture하면 무명 클래스 안에 capture한 변수를 저장하는 필드가 생긴다.
                     System.out.println(var1);
                 }
             }));
         }
     };*/


    // SAM 생성자는 람다를 함수형 인터페이스로 명시적으로 변경한다.
    // SAM 생성자 이름은 사용하려는 함수형 인터페이스(추상 메서드가 하나 뿐인 인터페이스)와 같다.
    // 1. 함수형 인터페이스의 인스턴스를 반환하는 경우
    fun createAllDoneRunnable(): Runnable {
        return Runnable { println("All done!") }
    }

    // 2. 변수에 람다로 생성한 함수형 인터페이스의 인스턴스를 저장하는 경우
    val listener = OnClickListener {view ->
        /*val text = when(view.id) {
            R.id.button1 -> "First button"
            R.id.button2 -> "Second button"
            else -> "Unknown button"
        }*/
    }


}