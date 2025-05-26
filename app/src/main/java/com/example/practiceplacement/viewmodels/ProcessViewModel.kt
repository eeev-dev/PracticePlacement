package com.example.practiceplacement.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceplacement.data.remote.api.InternResponse
import com.example.practiceplacement.data.remote.api.ReviewResponse
import com.example.practiceplacement.data.remote.models.Review
import com.example.practiceplacement.data.remote.repository.InternRepository
import com.example.practiceplacement.data.remote.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcessViewModel @Inject constructor(
    private val repository: InternRepository,
    private val reviewRepository: ReviewRepository
) : ViewModel() {

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
            loadIntern(id, context)
            isRefreshing.value = false
        }
    }

    fun loadIntern(internId: Int, context: Context) {
        if (internId != -1) {
            viewModelScope.launch {
                val result = repository.getIntern(internId)
                result.onSuccess {
                    internInfo = it
                    println(internInfo?.place_id)
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
        repository.clearCache()
    }

    private fun saveUserDataToPrefs(context: Context, place: String?) {
        val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPrefs.edit()
            .putString("place", place)
            .apply()
    }

    private val _resultReview = MutableLiveData<Result<ReviewResponse>>()
    val resultReview: LiveData<Result<ReviewResponse>> = _resultReview

    fun sendReview(rating: Int, text: String, date: String, placeId: Int) {
        viewModelScope.launch {
            val res = reviewRepository.sendReview(rating, text, date, placeId)
            _resultReview.value = res
            res.onSuccess {
                val message = it.message
                Log.d("ReviewVM", "Ответ сервера: $message")
            }
            res.onFailure {
                val message = it.message
                Log.d("ReviewVM", "Ответ сервера: $message")
            }
        }
    }
}