package ccl.android.currencylistdemo.feature.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ccl.android.currencylistdemo.R
import ccl.android.currencylistdemo.databinding.ItemCurrencyBinding
import ccl.android.currencylistdemo.model.CurrencyInfo

class CurrencyListAdapter(private val delegate: CurrencyClickDelegate) :
    ListAdapter<CurrencyInfo, CurrencyInfoViewHolder>(DiffUtilItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyInfoViewHolder {
        return CurrencyInfoViewHolder(parent, delegate)
    }

    override fun onBindViewHolder(holder: CurrencyInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface CurrencyClickDelegate {
        fun onClickCurrency(info: CurrencyInfo)
    }
}

class CurrencyInfoViewHolder(
    parent: ViewGroup,
    private val delegate: CurrencyListAdapter.CurrencyClickDelegate
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
) {
    private val viewBinding = ItemCurrencyBinding.bind(itemView)

    init {
        viewBinding.root.setOnClickListener { currencyInfo?.let(delegate::onClickCurrency) }
    }

    /**
     * The last bound data is kept for later use like callbacks (onclick)
     */
    private var currencyInfo: CurrencyInfo? = null

    fun bind(currencyInfo: CurrencyInfo) {
        this.currencyInfo = currencyInfo
        viewBinding.textName.text = currencyInfo.name
        viewBinding.textSymbol.text = currencyInfo.symbol
    }
}

private object DiffUtilItemCallback : DiffUtil.ItemCallback<CurrencyInfo>() {
    override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}
