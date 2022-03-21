package ccl.android.currencylistdemo.db.currency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ccl.android.currencylistdemo.TABLE_CURRENCY
import ccl.android.currencylistdemo.model.CurrencyInfo

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM $TABLE_CURRENCY")
    fun getAll(): List<CurrencyInfo>

    @Query("SELECT * FROM $TABLE_CURRENCY ORDER BY name ASC")
    fun getAllSortedByName(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencies: List<CurrencyInfo>)
}
