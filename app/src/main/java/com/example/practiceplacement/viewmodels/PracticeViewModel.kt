package com.example.practiceplacement.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceplacement.data.remote.ApiClient.internApi
import com.example.practiceplacement.data.remote.api.InternResponse
import com.example.practiceplacement.data.remote.repository.InternRepository
import com.example.practiceplacement.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(
    private val repository: InternRepository,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val dataStoreManager = DataStoreManager(appContext)

    var internInfo by mutableStateOf<InternResponse?>(null)
        private set

    var message by mutableStateOf("")

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isRefreshing = mutableStateOf(false)

    fun refresh(context: Context) {
        val id = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE).getInt("intern_id", -1)
        viewModelScope.launch {
            isRefreshing.value = true
            clearData()
            loadIntern(id)
            isRefreshing.value = false
        }
    }

    fun loadIntern(internId: Int) {
        if (internId == -1) {
            message = "Пользователь не найден"
            return
        }

        viewModelScope.launch {
            val savedStatus = dataStoreManager.getStatus()

            if (savedStatus == "Подтвержден") {
                internInfo = InternResponse(
                    status = savedStatus,
                    place = dataStoreManager.getPlace()
                )
            } else {
                val result = repository.getIntern(internId)
                result.onSuccess {
                    internInfo = it
                    if (it.status == "Подтвержден") {
                        dataStoreManager.saveAfterConfirm(it.place.toString())
                    }
                    errorMessage = null
                }.onFailure {
                    message = it.message.toString()
                    errorMessage = it.message ?: "Неизвестная ошибка"
                }
            }
        }
    }

    fun clearData() {
        internInfo = null
        repository.clearCache()
    }
}

