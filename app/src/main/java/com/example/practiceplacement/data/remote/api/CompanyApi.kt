package com.example.practiceplacement.data.remote.api

import com.example.practiceplacement.data.remote.models.Place
import retrofit2.Call
import retrofit2.http.GET

interface CompanyApi {
    @GET("/api/places")
    suspend fun getPlaces(): List<Place>
}