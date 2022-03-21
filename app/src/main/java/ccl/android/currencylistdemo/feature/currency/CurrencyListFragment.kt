package ccl.android.currencylistdemo.feature.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ccl.android.currencylistdemo.R
import ccl.android.currencylistdemo.databinding.FragmentCurrencyListBinding
import ccl.android.currencylistdemo.extension.showToast
import ccl.android.currencylistdemo.model.CurrencyInfo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CurrencyListFragment : Fragment() {
    companion object {
        const val TAG = "CurrencyListFragment"
    }

    private var binding: FragmentCurrencyListBinding? = null
    private val viewModel: CurrencyListViewModel by sharedViewModel()
    private val clickDelegate = object : CurrencyListAdapter.CurrencyClickDelegate {
        override fun onClickCurrency(info: CurrencyInfo) {
            context?.showToast(info.toString())
        }
    }
    private val adapter = CurrencyListAdapter(clickDelegate)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCurrencyListBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    private fun initView() {
        binding?.recyclerCurrency?.apply {
            adapter = this@CurrencyListFragment.adapter
        }
    }

    private fun observeData() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                if (it) context?.showToast(getString(R.string.loading_start))
            }
            currencyList.observe(viewLifecycleOwner) { adapter.submitList(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
