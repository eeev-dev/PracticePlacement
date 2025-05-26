package com.example.practiceplacement.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.practiceplacement.data.remote.ApiClient
import com.example.practiceplacement.data.remote.ApiClient.companyApi
import com.example.practiceplacement.data.remote.api.InternResponse
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.data.remote.repository.CompaniesRepository
import com.example.practiceplacement.data.remote.repository.InternRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(
    private val companiesRepository: CompaniesRepository,
    private val internsRepository: InternRepository
) : ViewModel() {

    var internInfo by mutableStateOf<InternResponse?>(null)
        private set

    var message by mutableStateOf("")

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isRefreshing = mutableStateOf(false)

    fun loadIntern(internId: Int) {
        if (internId != -1) {
            viewModelScope.launch {
                val result = internsRepository.getIntern(internId)
                result.onSuccess {
                    internInfo = it
                    println(internInfo?.status)
                    errorMessage = null
                }.onFailure {
                    message = it.message.toString()
                    errorMessage = it.message ?: "Неизвестная ошибка"
                }
            }
        }
        else message = "Пользователь не найден"
    }

    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> = _places

    init {
        fetchPlaces()
    }

    fun refresh(context: Context) {
        val id = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE).getInt("intern_id", -1)
        viewModelScope.launch {
            isRefreshing.value = true
            clearData()
            loadIntern(id)
            _places.value = emptyList()
            companiesRepository.clearCache()
            fetchPlaces()
            isRefreshing.value = false
        }
    }

    fun clearData() {
        internInfo = null
        internsRepository.clearCache()
        companiesRepository.clearCache()
    }

    private fun fetchPlaces() {
        viewModelScope.launch {
            val result = companiesRepository.getCompanies()
            if (result.isSuccess) {
                _places.value = result.getOrNull() ?: emptyList()
            } else {
                Log.e("SelectionViewModel", "Ошибка при загрузке", result.exceptionOrNull())
            }
        }
    }
}
