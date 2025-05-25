package com.example.practiceplacement.data.remote.models

data class Place(
    val id: Int,
    val title: String,
    val occupation: String,
    val places: Int,
    val max_places: Int,
    val requirements: String = "",
    val outlook: String = "",
    val contacts: String = ""
)