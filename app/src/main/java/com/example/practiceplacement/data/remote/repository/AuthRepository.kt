package com.example.practiceplacement.data.remote.repository

import com.example.practiceplacement.data.remote.api.LoginApi
import com.example.practiceplacement.data.remote.api.LoginApi.LoginRequest
import com.example.practiceplacement.data.remote.api.LoginResponse

class AuthRepository(private val api: LoginApi) {

    suspend fun login(studentId: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(studentId))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                if (body.success) {
                    Result.success(body)  // Возвращаем весь объект LoginResponse
                } else {
                    Result.failure(Exception(body.message))
                }
            } else {
                Result.failure(Exception("Ошибка сервера: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
