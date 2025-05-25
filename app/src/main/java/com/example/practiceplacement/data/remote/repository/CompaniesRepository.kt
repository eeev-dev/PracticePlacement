package com.example.practiceplacement.data.remote.repository

import com.example.practiceplacement.data.remote.ApiClient
import com.example.practiceplacement.data.remote.api.CompanyApi
import com.example.practiceplacement.data.remote.api.InternApi
import com.example.practiceplacement.data.remote.api.InternRequest
import com.example.practiceplacement.data.remote.api.InternResponse
import com.example.practiceplacement.data.remote.models.Place

class CompaniesRepository(private val companyApi: CompanyApi) {

    val companiesRepository = ApiClient.companyApi.getPlaces()

    private var cachedCompanies: List<Place>? = null

    suspend fun getCompanies(): Result<List<Place>> {
        cachedCompanies?.let {
            return Result.success(it)
        }

        return try {
            val places = companyApi.getPlaces()
            cachedCompanies = places
            Result.success(places)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun clearCache() {
        cachedCompanies = null
    }
}