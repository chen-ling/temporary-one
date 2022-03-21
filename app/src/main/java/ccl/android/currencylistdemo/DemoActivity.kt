package ccl.android.currencylistdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ccl.android.currencylistdemo.databinding.ActivityDemoBinding
import ccl.android.currencylistdemo.feature.currency.CurrencyListFragment
import ccl.android.currencylistdemo.feature.currency.CurrencyListFragment.Companion.TAG
import ccl.android.currencylistdemo.feature.currency.CurrencyListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityDemoBinding
    private val viewModel: CurrencyListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initView()
        openCurrencyListFragment()
    }

    private fun initView() {
        with(viewBinding) {
            textLoadData.setOnClickListener {
                viewModel.loadData()
            }
            textSort.setOnClickListener {
                viewModel.sort()
            }
        }
    }

    private fun openCurrencyListFragment() {
        supportFragmentManager.commit(allowStateLoss = true) {
            add(R.id.fragment_container, CurrencyListFragment::class.java, null)
            addToBackStack(TAG)
        }
    }

}
