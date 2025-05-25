package com.example.practiceplacement.data.remote

import com.example.practiceplacement.data.remote.api.CompanyApi
import com.example.practiceplacement.data.remote.api.InternApi
import com.example.practiceplacement.data.remote.api.LoginApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://192.168.1.104:8080"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val loginApi: LoginApi by lazy {
        retrofit.create(LoginApi::class.java)
    }

    val internApi: InternApi by lazy {
        retrofit.create(InternApi::class.java)
    }

    val companyApi: CompanyApi by lazy {
        retrofit.create(CompanyApi::class.java)
    }
}
