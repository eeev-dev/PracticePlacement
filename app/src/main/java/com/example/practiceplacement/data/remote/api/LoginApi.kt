package com.example.practiceplacement.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class LoginResponse(
    val success: Boolean,
    val id: Int?,
    val head_teacher: String?,
    val place: String?,
    val message: String
)

interface LoginApi {
    data class LoginRequest(val student_id: String)

    @POST("/intern/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}