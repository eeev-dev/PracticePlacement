package com.example.practiceplacement.data.remote.repository

import android.content.Context
import com.example.practiceplacement.data.remote.api.InternApi
import com.example.practiceplacement.data.remote.api.InternPlaceRequest
import com.example.practiceplacement.data.remote.api.InternRequest
import com.example.practiceplacement.data.remote.api.InternResponse
import com.example.practiceplacement.utils.DataStoreManager
import com.example.practiceplacement.utils.PrefKeys
import com.example.practiceplacement.utils.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class InternRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val internApi: InternApi
) {
    private val dataStore = DataStoreManager(context)

    suspend fun getStatus(): String {
        return dataStore.getStatus()
    }

    suspend fun getId(): Int  {
        val prefs = context.dataStore.data.first()
        val idString = prefs[PrefKeys.ID] ?: return -1
        return idString.toIntOrNull() ?: -1
    }

    suspend fun getPlace(): String {
        return dataStore.getPlace()
    }

    suspend fun getTeacher(): String {
        return dataStore.getTeacher()
    }

    private var cachedInternInfo: InternResponse? = null

    suspend fun getIntern(internId: Int): Result<InternResponse> {
        cachedInternInfo?.let {
            return Result.success(it)
        }

        return try {
            val response = internApi.getIntern(InternRequest(internId))
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.success) {
                    cachedInternInfo = body
                    Result.success(body)
                } else {
                    Result.failure(Exception(body?.message ?: "Ошибка данных"))
                }
            } else {
                Result.failure(Exception("Ошибка сервера: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendIntern(internId: Int, placeId: Int): Result<InternResponse> {
        return try {
            val response = internApi.postIntern(InternPlaceRequest(internId, placeId))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Ошибка: ${response.code()} — ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun clearCache() {
        cachedInternInfo = null
    }
}
