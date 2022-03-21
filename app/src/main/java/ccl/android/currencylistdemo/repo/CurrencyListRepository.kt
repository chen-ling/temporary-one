package ccl.android.currencylistdemo.repo

import android.content.Context
import ccl.android.currencylistdemo.R
import ccl.android.currencylistdemo.db.currency.CurrencyDao
import ccl.android.currencylistdemo.model.CurrencyInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface CurrencyListRepository {
    suspend fun getCurrencyList(): List<CurrencyInfo>
}

class CurrencyListRepositoryImpl(
    private val appContext: Context,
    private val gson: Gson,
    private val currencyDao: CurrencyDao
) : CurrencyListRepository {

    companion object {
        private val dataInitMutex = Mutex()
        private var dataInitialized = false
    }

    private suspend fun checkAndInitializesData() {
        dataInitMutex.withLock {
            if (dataInitialized) return@withLock
            val count = currencyDao.getCount()
            if (count != 0L) return@withLock
            dataInitialized = true

            val currencyList = appContext.resources.openRawResource(R.raw.currency_list).use { inputStream ->
                val currencyInfoListType = object : TypeToken<List<CurrencyInfo>>() {}.type
                gson.fromJson<List<CurrencyInfo>>(inputStream.reader(), currencyInfoListType)
            }
            currencyDao.insertAll(currencyList)
        }
    }

    override suspend fun getCurrencyList(): List<CurrencyInfo> {
        checkAndInitializesData()
        return currencyDao.getAll()
    }
}
