package ccl.android.currencylistdemo.feature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ccl.android.currencylistdemo.BaseTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.koin.test.KoinTest

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseViewModelTest : BaseTest(), KoinTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    val testDispatcher = UnconfinedTestDispatcher()
    private val mainThreadSurrogate = Dispatchers.Unconfined

    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }
}
