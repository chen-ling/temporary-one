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
    suspend fun getAll(): List<CurrencyInfo>

    @Query("SELECT * FROM $TABLE_CURRENCY ORDER BY name ASC")
    suspend fun getAllSortedByName(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<CurrencyInfo>)

    @Query("SELECT COUNT(id) FROM $TABLE_CURRENCY")
    suspend fun getCount(): Long
}
