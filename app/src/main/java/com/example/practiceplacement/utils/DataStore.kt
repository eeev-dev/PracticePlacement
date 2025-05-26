package com.example.practiceplacement.utils

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_prefs")

object PrefKeys {
    val STATUS = stringPreferencesKey("status")
    val HEAD_TEACHER = stringPreferencesKey("head_teacher")
    val PLACE = stringPreferencesKey("place")
    val ID = stringPreferencesKey("intern_id")
}

