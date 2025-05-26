package com.example.practiceplacement.data.remote.api

import android.os.Message
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.data.remote.models.Review
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDate

interface ReviewApi {
    @POST("/api/reviews")
    suspend fun getReviews(@Body request: GetReviewRequest): List<Review>

    @POST("/review/post")
    suspend fun postReview(@Body request: ReviewRequest): Response<ReviewResponse>
}

data class GetReviewRequest(
    val place_id: Int
)

data class ReviewRequest(
    val rating: Int,
    val text: String,
    val date: String,
    val place_id: Int
)

data class ReviewResponse(
    val success: Boolean,
    val message: String
)