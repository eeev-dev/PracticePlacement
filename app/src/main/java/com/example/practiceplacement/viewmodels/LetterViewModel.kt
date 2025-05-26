package com.example.practiceplacement.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceplacement.data.remote.api.InternResponse
import com.example.practiceplacement.data.remote.api.LetterApi
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.data.remote.repository.CompaniesRepository
import com.example.practiceplacement.data.remote.repository.InternRepository
import com.example.practiceplacement.viewmodels.LoginViewModel.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File
import javax.inject.Inject
import kotlin.Result


@HiltViewModel
class LetterViewModel @Inject constructor (
    private val api: LetterApi,
    private val internRepository: InternRepository
) : ViewModel() {
    var result by mutableStateOf<Result<InternResponse>?>(null)
        private set

    fun refreshStatus() {
        internRepository.clearCache()
    }

    fun sendPlace(context: Context, imageFile: File) {
        val internId = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getInt("intern_id", -1)

        if (internId != -1) {
            viewModelScope.launch {
                try {
                    val requestFile = imageFile
                        .asRequestBody("image/*".toMediaTypeOrNull())

                    val body = MultipartBody.Part.createFormData(
                        "image", imageFile.name, requestFile
                    )

                    result = api.sendLetter(body, internId)
                } catch (e: Exception) {
                    result = Result.failure(e)
                }
            }
        } else {
            result = Result.failure(Exception("intern_id не найден"))
        }
    }
}