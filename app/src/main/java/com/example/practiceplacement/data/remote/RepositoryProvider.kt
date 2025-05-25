package com.example.practiceplacement.data.remote

import com.example.practiceplacement.data.remote.repository.AuthRepository
import com.example.practiceplacement.data.remote.repository.CompaniesRepository
import com.example.practiceplacement.data.remote.repository.InternRepository

object RepositoryProvider {
    val companiesRepository: CompaniesRepository by lazy {
        CompaniesRepository(ApiClient.companyApi)
    }
    val authRepository: AuthRepository by lazy {
        AuthRepository(ApiClient.loginApi)
    }
    val internsRepository: InternRepository by lazy {
        InternRepository(ApiClient.internApi)
    }
}