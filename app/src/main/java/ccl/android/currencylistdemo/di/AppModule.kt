package ccl.android.currencylistdemo.di

import androidx.room.Room
import ccl.android.currencylistdemo.BuildConfig
import ccl.android.currencylistdemo.db.AppDatabase
import ccl.android.currencylistdemo.feature.currency.CurrencyListViewModel
import ccl.android.currencylistdemo.repo.CurrencyListRepository
import ccl.android.currencylistdemo.repo.CurrencyListRepositoryImpl
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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

    single<CurrencyListRepository> {
        CurrencyListRepositoryImpl(androidApplication(), get(), get())
    }

    viewModel { CurrencyListViewModel(get()) }
}
