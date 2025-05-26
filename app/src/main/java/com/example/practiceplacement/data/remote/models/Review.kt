package com.example.practiceplacement.data.remote.models

import java.time.LocalDate

data class Review (
    val date: String,
    val id: Int,
    val id_place: Int,
    val rating: Int,
    val text: String
)