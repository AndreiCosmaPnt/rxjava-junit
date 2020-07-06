package com.github.technoir42.rxjava3.junit

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.functions.Function

class RxAndroidPluginsInvoker {
    private val rxAndroidPlugins: Class<*>? = try {
        Class.forName("io.reactivex.rxjava3.android.plugins.RxAndroidPlugins")
    } catch (e: ClassNotFoundException) {
        null
    }

    fun setMainThreadSchedulerHandler(handler: Function<Scheduler, Scheduler>) {
        rxAndroidPlugins
            ?.getDeclaredMethod("setMainThreadSchedulerHandler", Function::class.java)
            ?.invoke(null, handler)
    }

    fun reset() {
        rxAndroidPlugins
            ?.getDeclaredMethod("reset")
            ?.invoke(null)
    }
}
