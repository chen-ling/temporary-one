package ccl.android.currencylistdemo.feature.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ccl.android.currencylistdemo.model.CurrencyInfo
import ccl.android.currencylistdemo.repo.CurrencyListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CurrencyListViewModel(
    private val repository: CurrencyListRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _currencyList = MutableLiveData<List<CurrencyInfo>>(emptyList())
    val currencyList: LiveData<List<CurrencyInfo>> = _currencyList
    val isLoading = MutableLiveData<Boolean>()
    private var previousSortJob: Job? = null

    fun loadData() {
        viewModelScope.launch(dispatcher) {
            isLoading.postValue(true)
            _currencyList.postValue(repository.getCurrencyList())
        }.invokeOnCompletion { isLoading.postValue(false) }
    }

    fun sort() {
        previousSortJob?.cancel()
        previousSortJob = viewModelScope.launch(dispatcher) {
            val currentList = currencyList.value ?: return@launch
            isLoading.postValue(true)
            _currencyList.postValue(currentList.sortedBy { it.name })
        }
        previousSortJob?.invokeOnCompletion { isLoading.postValue(false) }
    }
}
