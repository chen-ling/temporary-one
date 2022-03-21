package ccl.android.currencylistdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ccl.android.currencylistdemo.databinding.ActivityDemoBinding
import ccl.android.currencylistdemo.feature.currency.CurrencyListFragment
import ccl.android.currencylistdemo.feature.currency.CurrencyListFragment.Companion.TAG

class DemoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        openCurrencyListFragment()
    }

    private fun openCurrencyListFragment() {
        supportFragmentManager.commit(allowStateLoss = true) {
            add(R.id.fragment_container, CurrencyListFragment::class.java, null)
            addToBackStack(TAG)
        }
    }

}
