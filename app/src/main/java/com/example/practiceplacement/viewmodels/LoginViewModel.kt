package com.example.practiceplacement.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceplacement.data.remote.ApiClient
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.data.remote.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.core.content.edit
import com.example.practiceplacement.data.remote.repository.InternRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var studentId by mutableStateOf("")
    private set

    var loginResult by mutableStateOf<LoginResult?>(null)
        private set

    var message by mutableStateOf("")

    fun onStudentIdChange(newId: String) {
        studentId = newId
    }

    fun onLoginClick(context: Context) {
        viewModelScope.launch {
            try {
                val result = repository.login(studentId)
                result.onSuccess { response ->
                    if (response.id != null) saveUserDataToPrefs(context, response.id, response.head_teacher, response.place)
                    loginResult = LoginResult.Success(response.message)
                }.onFailure {
                    loginResult = LoginResult.Error(it.message ?: "Неизвестная ошибка")
                }
            } catch (e: Exception) {
                loginResult = LoginResult.Error("Сервер недоступен: ${e.message}")
            }
        }
    }

    sealed class LoginResult {
        data class Success(val message: String) : LoginResult()
        data class Error(val message: String) : LoginResult()
    }

    private fun saveUserDataToPrefs(context: Context, id: Int, headTeacher: String?, place: String?) {
        val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPrefs.edit()
            .putBoolean("isLogin", true)
            .putInt("intern_id", id)
            .putString("head_teacher", headTeacher)
            .putString("place", place)
            .apply()
    }
}