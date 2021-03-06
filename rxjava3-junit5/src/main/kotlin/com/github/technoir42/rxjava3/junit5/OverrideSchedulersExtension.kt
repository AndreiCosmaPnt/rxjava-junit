package com.github.technoir42.rxjava3.junit5

import com.github.technoir42.rxjava3.junit.RxAndroidPluginsInvoker
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Replaces standard schedulers with the specified scheduler (by default [Schedulers.trampoline])
 * before test execution and restores default schedulers after test execution.
 */
class OverrideSchedulersExtension(
    private val scheduler: Scheduler = Schedulers.trampoline()
) : BeforeEachCallback, AfterEachCallback {

    private val rxAndroidPluginsInvoker = RxAndroidPluginsInvoker()

    override fun beforeEach(context: ExtensionContext) {
        val handler = Function<Scheduler, Scheduler> { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler(handler)
        RxJavaPlugins.setIoSchedulerHandler(handler)
        RxJavaPlugins.setNewThreadSchedulerHandler(handler)
        RxJavaPlugins.setSingleSchedulerHandler(handler)
        rxAndroidPluginsInvoker.setMainThreadSchedulerHandler(handler)
    }

    override fun afterEach(context: ExtensionContext) {
        RxJavaPlugins.reset()
        rxAndroidPluginsInvoker.reset()
    }
}
