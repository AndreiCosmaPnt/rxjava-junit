package com.github.technoir42.rxjava3.junit4

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test

class OverrideSchedulersRuleTest {
    private val scheduler = TestScheduler()

    @Rule
    @JvmField
    val overrideSchedulersRule = OverrideSchedulersRule(scheduler)

    @Test
    fun `Overrides standard schedulers`() {
        assertSame(scheduler, Schedulers.computation())
        assertSame(scheduler, Schedulers.io())
        assertSame(scheduler, Schedulers.newThread())
        assertSame(scheduler, Schedulers.single())
        assertSame(scheduler, AndroidSchedulers.mainThread())
    }
}
