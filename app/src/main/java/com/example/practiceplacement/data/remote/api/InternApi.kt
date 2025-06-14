package com.example.practiceplacement.data.remote.api

import com.example.practiceplacement.data.remote.api.LoginApi.LoginRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface InternApi {
    @POST("/intern/post")
    suspend fun postIntern(@Body request: InternPlaceRequest): Response<InternResponse>

    @POST("/intern/get")
    suspend fun getIntern(
        @Body request: InternRequest
    ): Response<InternResponse>
}

data class InternRequest(val intern_id: Int)

data class InternPlaceRequest(
    val intern_id: Int,
    val place_id: Int
)

data class InternResponse(
    val success: Boolean = false,
    val status: String? = "",
    val deadline: String? = "",
    val place: String? = "",
    val place_id: Int? = -1,
    val message: String? = null
)