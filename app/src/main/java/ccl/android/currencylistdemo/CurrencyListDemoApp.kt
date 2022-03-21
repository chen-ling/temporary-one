package ccl.android.currencylistdemo

import android.app.Application
import ccl.android.currencylistdemo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CurrencyListDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        synchronizedInit()
    }

    private fun synchronizedInit() {
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@CurrencyListDemoApp)
            modules(listOf(appModule))
        }
    }
}
