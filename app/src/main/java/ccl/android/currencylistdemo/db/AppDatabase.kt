package ccl.android.currencylistdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ccl.android.currencylistdemo.db.currency.CurrencyDao
import ccl.android.currencylistdemo.model.CurrencyInfo

@Database(
    version = 1,
    entities = [CurrencyInfo::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyListDao(): CurrencyDao
}
