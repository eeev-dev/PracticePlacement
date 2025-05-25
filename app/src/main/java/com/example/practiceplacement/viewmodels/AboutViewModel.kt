package com.example.practiceplacement.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceplacement.data.remote.api.InternResponse
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.data.remote.repository.CompaniesRepository
import com.example.practiceplacement.data.remote.repository.InternRepository
import com.example.practiceplacement.viewmodels.LoginViewModel.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor (
    private val companiesRepository: CompaniesRepository,
    private val internRepository: InternRepository
) : ViewModel() {

    var place: Place? = null

    fun getPlace(id: Int) = viewModelScope.launch {
        val result = companiesRepository.getCompanies()
        if (result.isSuccess) {
            val list = result.getOrNull()
            place = list?.find { it.id == id }
        } else {
            println("Ошибка")
        }
    }

    fun refreshStatus() {
        internRepository.clearCache()
    }

    private val _result = MutableLiveData<Result<InternResponse>>()
    val result: LiveData<Result<InternResponse>> = _result

    fun sendPlace(context: Context, placeId: Int) {
        val internId = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getInt("intern_id", -1)

        if (internId != -1) {
            viewModelScope.launch {
                val res = internRepository.sendIntern(internId, placeId)
                _result.value = res
            }
        } else {
            _result.value = Result.failure(Exception("intern_id не найден"))
        }
    }
}