package com.github.technoir42.rxjava3.junit5

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class OverrideSchedulersExtensionProgrammaticRegistrationTest {
    private val scheduler = TestScheduler()

    @RegisterExtension
    @JvmField
    val overrideSchedulersExtension = OverrideSchedulersExtension(scheduler)

    @Test
    fun `Overrides standard schedulers`() {
        assertSame(scheduler, Schedulers.computation())
        assertSame(scheduler, Schedulers.io())
        assertSame(scheduler, Schedulers.newThread())
        assertSame(scheduler, Schedulers.single())
        assertSame(scheduler, AndroidSchedulers.mainThread())
    }
}
