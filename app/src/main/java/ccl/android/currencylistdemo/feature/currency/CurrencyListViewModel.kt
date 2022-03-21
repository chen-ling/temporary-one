package ccl.android.currencylistdemo.feature.currency

import android.util.Log
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
    companion object {
        private const val TAG = "CurrencyListViewModel"
    }

    private val _currencyList = MutableLiveData<List<CurrencyInfo>>(emptyList())
    val currencyList: LiveData<List<CurrencyInfo>> = _currencyList
    val isLoading = MutableLiveData<Boolean>()
    private var previousSortJob: Job? = null

    private fun isLoaded(): Boolean = !_currencyList.value.isNullOrEmpty()

    fun loadData() {
        if (isLoaded() || isLoading.value == true) return
        isLoading.value = true
        viewModelScope.launch(dispatcher) {
            try {
                _currencyList.postValue(repository.getCurrencyList())
            } catch (e: Exception) {
                Log.e(TAG, "load data failed: $e")
            }
        }.invokeOnCompletion { isLoading.postValue(false) }
    }

    fun sort() {
        if (!isLoaded() || isLoading.value == true) return
        isLoading.value = true
        previousSortJob?.cancel()
        previousSortJob = viewModelScope.launch(dispatcher) {
            val currentList = currencyList.value ?: return@launch
            _currencyList.postValue(currentList.sortedBy { it.name })
        }
        previousSortJob?.invokeOnCompletion { isLoading.postValue(false) }
    }
}
