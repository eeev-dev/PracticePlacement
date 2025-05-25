package com.example.practiceplacement.viewmodels

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
import com.example.practiceplacement.data.remote.RepositoryProvider
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
    private val repository: CompaniesRepository
) : ViewModel() {

    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> = _places

    init {
        fetchPlaces()
    }

    fun refreshPlaces() {
        _places.value = emptyList()
        repository.clearCache()
        fetchPlaces()
    }

    private fun fetchPlaces() {
        viewModelScope.launch {
            val result = repository.getCompanies()
            if (result.isSuccess) {
                _places.value = result.getOrNull() ?: emptyList()
            } else {
                Log.e("SelectionViewModel", "Ошибка при загрузке", result.exceptionOrNull())
            }
        }
    }
}
