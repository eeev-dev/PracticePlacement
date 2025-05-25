package com.example.practiceplacement.data.remote.repository

import com.example.practiceplacement.data.remote.api.InternApi
import com.example.practiceplacement.data.remote.api.InternPlaceRequest
import com.example.practiceplacement.data.remote.api.InternRequest
import com.example.practiceplacement.data.remote.api.InternResponse

class InternRepository(private val internApi: InternApi) {

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
