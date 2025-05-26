package com.example.practiceplacement.data.remote.repository

import com.example.practiceplacement.data.remote.ApiClient.internApi
import com.example.practiceplacement.data.remote.api.CompanyApi
import com.example.practiceplacement.data.remote.api.GetReviewRequest
import com.example.practiceplacement.data.remote.api.InternApi
import com.example.practiceplacement.data.remote.api.InternPlaceRequest
import com.example.practiceplacement.data.remote.api.InternRequest
import com.example.practiceplacement.data.remote.api.InternResponse
import com.example.practiceplacement.data.remote.api.ReviewApi
import com.example.practiceplacement.data.remote.api.ReviewRequest
import com.example.practiceplacement.data.remote.api.ReviewResponse
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.data.remote.models.Review
import java.time.LocalDate
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewApi: ReviewApi
) {
    private var cachedReviews: List<Review>? = null

    suspend fun getReviews(placeId: Int): Result<List<Review>> {
        cachedReviews?.let { return Result.success(it) }
        return try {
            val reviews = reviewApi.getReviews(GetReviewRequest(placeId))
            cachedReviews = reviews
            Result.success(reviews)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendReview(rating: Int, text: String, date: String, placeId: Int): Result<ReviewResponse> {
        return try {
            val response = reviewApi.postReview(ReviewRequest(rating, text, date.toString(), placeId))
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
        cachedReviews = null
    }
}