package ccl.android.currencylistdemo.di

import androidx.room.Room
import ccl.android.currencylistdemo.BuildConfig
import ccl.android.currencylistdemo.db.AppDatabase
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { Gson() }
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            BuildConfig.DB_NAME
        ).build()
    }

    single {
        val appDb: AppDatabase = get()
        appDb.currencyListDao()
    }
}
