package com.example.learnkotlin.pattern

import android.content.Context

// DCL(Double Checked Locking)
class Singleton private constructor(){

    companion object {
        @Volatile
        private var instance: Singleton? = null
        private lateinit var context: Context

        fun getInstance(context: Context): Singleton {
            return instance ?: synchronized(this) {
                instance ?: Singleton().also {
                    this.context = context
                    instance = it
                }
            }
        }
    }
}








/*
package com.example.learnkotlin.pattern;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
    mv = {1, 7, 1},
    k = 1,
    d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"},
    d2 = {"Lcom/example/learnkotlin/pattern/Singleton;", "", "()V", "Companion", "LearnKotlin.app.main"}
)
public final class Singleton {
    private static Singleton instance;
    private static Context context;
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker)null);

    private Singleton() {
    }

    // $FF: synthetic method
    public static final Singleton access$getInstance$cp() {
        return instance;
    }

    // $FF: synthetic method
    public static final void access$setInstance$cp(Singleton var0) {
        instance = var0;
    }

    // $FF: synthetic method
    public Singleton(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    // $FF: synthetic method
    public static final Context access$getContext$cp() {
        return context;
    }

    // $FF: synthetic method
    public static final void access$setContext$cp(Context var0) {
        context = var0;
    }

    @Metadata(
        mv = {1, 7, 1},
        k = 1,
        d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"},
        d2 = {"Lcom/example/learnkotlin/pattern/Singleton$Companion;", "", "()V", "context", "Landroid/content/Context;", "instance", "Lcom/example/learnkotlin/pattern/Singleton;", "getInstance", "LearnKotlin.app.main"}
    )
    public static final class Companion {
        @NotNull
        public final Singleton getInstance(@NotNull Context param1) {
            // $FF: Couldn't be decompiled
        }

        private Companion() {
        }

        // $FF: synthetic method
        public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
*/
