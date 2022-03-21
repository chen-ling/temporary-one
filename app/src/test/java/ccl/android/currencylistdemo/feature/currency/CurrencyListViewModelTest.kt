package ccl.android.currencylistdemo.feature.currency

import ccl.android.currencylistdemo.feature.BaseViewModelTest
import ccl.android.currencylistdemo.model.CurrencyInfo
import ccl.android.currencylistdemo.repo.CurrencyListRepository
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.test.KoinTestRule
import org.koin.test.inject

@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyListViewModelTest : BaseViewModelTest() {
    companion object {
        private val mockCurrency1 = CurrencyInfo(id = "1", name = "a", symbol = "1")
        private val mockCurrency2 = CurrencyInfo(id = "2", name = "b", symbol = "2")
        private val mockCurrency3 = CurrencyInfo(id = "3", name = "c", symbol = "3")
        private val mockCurrencyList = listOf(mockCurrency3, mockCurrency2, mockCurrency1)
        private val sortedMockCurrencyList = listOf(mockCurrency1, mockCurrency2, mockCurrency3)
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(mockAppModules)
    }

    private val mockAppModules = module {
        single { mockkClass(CurrencyListRepository::class) }
        viewModel { CurrencyListViewModel(get(), testDispatcher) }
    }

    private val viewModel: CurrencyListViewModel by inject()
    private val repo: CurrencyListRepository by inject()

    @Test
    fun `load currencies and sort it successfully`() {
        coEvery { repo.getCurrencyList() } returns mockCurrencyList
        runTest {
            viewModel.loadData()
            assertEquals(mockCurrencyList, viewModel.currencyList.value)
            assertEquals(false, viewModel.isLoading.value)
            viewModel.sort()
            assertEquals(sortedMockCurrencyList, viewModel.currencyList.value)
            assertEquals(false, viewModel.isLoading.value)
        }
    }
}
