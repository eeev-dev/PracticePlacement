package com.example.practiceplacement.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceplacement.data.remote.ApiClient.internApi
import com.example.practiceplacement.data.remote.RepositoryProvider
import com.example.practiceplacement.data.remote.api.InternResponse
import com.example.practiceplacement.data.remote.repository.InternRepository
import kotlinx.coroutines.launch

class PracticeViewModel() : ViewModel() {
    private val repository = RepositoryProvider.internsRepository

    var internInfo by mutableStateOf<InternResponse?>(null)
        private set

    var message by mutableStateOf("")

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadIntern(internId: Int) {
        if (internId != -1) {
            viewModelScope.launch {
                val result = repository.getIntern(internId)
                result.onSuccess {
                    internInfo = it
                    errorMessage = null
                }.onFailure {
                    message = it.message.toString()
                    errorMessage = it.message ?: "Неизвестная ошибка"
                }
            }
        }
        else message = "Пользователь не найден"
    }

    fun clearData() {
        internInfo = null
        InternRepository(internApi).clearCache()
    }
}

