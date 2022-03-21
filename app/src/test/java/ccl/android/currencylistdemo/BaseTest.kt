package ccl.android.currencylistdemo

import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic

open class BaseTest {
    init {
        mockLog()
    }

    private fun mockLog() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }
}
