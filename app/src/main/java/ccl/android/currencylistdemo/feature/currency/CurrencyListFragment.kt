package ccl.android.currencylistdemo.feature.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ccl.android.currencylistdemo.databinding.FragmentCurrencyListBinding

class CurrencyListFragment : Fragment() {
    companion object {
        const val TAG = "CurrencyListFragment"
    }

    private var binding: FragmentCurrencyListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCurrencyListBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
