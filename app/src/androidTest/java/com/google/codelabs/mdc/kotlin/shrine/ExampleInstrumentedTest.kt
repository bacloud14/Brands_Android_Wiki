package com.google.codelabs.mdc.kotlin.shrine

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented brands dataset, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under brands dataset.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        assertEquals("com.bacloud.brands", appContext.packageName)
    }
}
