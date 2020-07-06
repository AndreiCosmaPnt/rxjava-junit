package com.github.technoir42.rxjava3.junit4

import com.github.technoir42.rxjava3.junit.RxAndroidPluginsInvoker
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Replaces standard schedulers with the specified scheduler (by default [Schedulers.trampoline])
 * before test execution and restores default schedulers after test execution.
 */
class OverrideSchedulersRule(
    private val scheduler: Scheduler = Schedulers.trampoline()
) : TestRule {

    private val rxAndroidPluginsInvoker = RxAndroidPluginsInvoker()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                val handler = Function<Scheduler, Scheduler> { scheduler }
                RxJavaPlugins.setComputationSchedulerHandler(handler)
                RxJavaPlugins.setIoSchedulerHandler(handler)
                RxJavaPlugins.setNewThreadSchedulerHandler(handler)
                RxJavaPlugins.setSingleSchedulerHandler(handler)
                rxAndroidPluginsInvoker.setMainThreadSchedulerHandler(handler)

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    rxAndroidPluginsInvoker.reset()
                }
            }
        }
    }
}
