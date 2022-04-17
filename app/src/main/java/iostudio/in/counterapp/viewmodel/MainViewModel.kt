package iostudio.`in`.counterapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iostudio.`in`.counterapp.model.Counter
import iostudio.`in`.counterapp.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _counts = MutableLiveData<Counter>()
    val counts: LiveData<Counter>
        get() = _counts

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _counts.postValue(Counter(mainRepository.getCounts()))
        }
    }

    fun updateCountByOne() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.updateCount(_counts.value?.counts ?: 0).also {
                fetchUsers()
            }
        }
    }
}